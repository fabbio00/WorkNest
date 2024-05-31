<template>
    <div class="mx-16">
      <v-data-table
        class="mx-auto my-16"
        :headers="headers"
        :items="employees"
        :sort-by="[{ key: 'surname', order: 'asc' }]"
        :header-props="headerProps"
        :item-props="itemProps"
        style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)"
      >
        <template v-slot:top>
          <v-toolbar flat class="bg-blue-grey-lighten-3">
            <v-toolbar-title>Employees</v-toolbar-title>
            
            <v-dialog v-model="dialogDelete" max-width="500px">
              <v-card>
                <v-card-title class="text-h5"
                  >Are you sure you want to delete this user?</v-card-title
                >
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue-darken-1" variant="text" @click="closeDelete"
                    >Cancel</v-btn
                  >
                  <v-btn
                    color="blue-darken-1"
                    variant="text"
                    @click="deleteItemConfirm"
                    >OK</v-btn
                  >
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog v-model="dialogEdit" max-width="600px">
                <v-card>
                    <v-card-title class="text-h5 text-center"
                    >Are you sure you want to change <br> {{ editedItem.name }} {{ editedItem.surname }}'s permissions to business?</v-card-title
                    >
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue-darken-1" variant="text" @click="closeEdit"
                        >Cancel</v-btn
                    >
                    <v-btn
                        color="blue-darken-1"
                        variant="text"
                        @click="editTypeItemConfirm"
                        >OK</v-btn
                    >
                    <v-spacer></v-spacer>
                    </v-card-actions>
                </v-card>
            </v-dialog>
          
            
            </v-toolbar>
        </template>

        <template v-slot:item.type="{ item }">
          <span>{{ item.type }}&nbsp;</span>
          <v-icon
            class="me-2"
            size="small"
            @click="editTypeItem(item)"
            :class="{
              disabled:
                item.status !== 'inactive' &&
                item.type !== 'BUSINESS'
                  ? false
                  : true,
            }"
          >
            mdi-pencil
          </v-icon>
        </template>
        <template v-slot:item.actions="{ item }">
        <v-icon
            size="small"
            @click="createBookingForEmployee(item)"
            :class="{
              disabled:
                item.status !== 'inactive'
                  ? false
                  : true,
            }"
          >
          mdi-calendar-check
          </v-icon>
          <v-icon
            size="small"
            @click="deleteItem(item)"
            :class="{
              disabled:
                item.status !== 'inactive'
                  ? false
                  : true,
            }"
          >
            mdi-delete
          </v-icon>
        </template>
  
        <template v-slot:item.startDate="{ item }">
          <v-icon
            class="me-2"
            size="small"
            :color="
              item.status == 'inactive'
                ? 'red' : 'green'
            "
          >
            {{
              item.status == "inactive"
                ? "mdi-calendar-remove"
                : item.status == "active"
                  ? "mdi-calendar-check"
                  : "mdi-calendar-clock"
            }}
          </v-icon>
          {{ item.startDate }}
        </template>
        <template v-slot:item.status="{ item }">
          <v-chip
            :color="
              item.status == 'inactive'
                ? 'red' : 'green'
            "
          >
            {{ item.status }}
          </v-chip>
        </template>
      </v-data-table>
    </div>
  </template>


<script>

/**
 * Vue component for managing employees.
 * This component allows users to view and manage employees, including displaying employee details in a table format.
 *
 * Features:
 * - Displays a table of employees with columns for surname, name, user type, status, and actions.
 * - Allows users to perform actions such as editing or deleting employees.
 *
 * Data properties:
 * @vue-data {string} companyId - The ID of the company.
 * @vue-data {string} userId - The ID of the current user.
 * @vue-data {boolean} dialog - Flag to control the visibility of a dialog.
 * @vue-data {boolean} dialogDelete - Flag to control the visibility of the delete confirmation dialog.
 * @vue-data {boolean} dialogEdit - Flag to control the visibility of the edit confirmation dialog.
 * @vue-data {boolean} showField - Flag to control the visibility of a field.
 * @vue-data {Array} headers - Array of objects representing table headers.
 * @vue-data {Object} headerProps - Object containing properties for table header styling.
 * @vue-data {Object} itemProps - Object containing properties for table item styling.
 * @vue-data {Array} employees - Array containing employee data to be displayed in the table.
 * @vue-data {number} editedIndex - Index of the currently edited employee.
 * @vue-data {Object} editedItem - Object representing the currently edited employee.
 * @vue-data {Object} editType - Object representing the edit type of the employee.
 *
 * Methods:
 * @vue-method {Function} deleteItem - Opens the delete confirmation dialog for the specified employee.
 * @vue-method {Function} deleteItemConfirm - Confirms the deletion of an employee.
 * @vue-method {Function} closeDelete - Closes the delete confirmation dialog.
 * @vue-method {Function} editTypeItem - Opens the edit confirmation dialog for the specified employee.
 * @vue-method {Function} editTypeItemConfirm - Confirms the editing of an employee.
 * @vue-method {Function} closeEdit - Closes the edit confirmation dialog.
 * @vue-method {Function} initialize_table - Initializes the table by fetching employee data and populating the employees array.
 *
 * Usage:
 * This component is used within a Vue application to manage employee data and display it in a table format.
 * It integrates with backend APIs to fetch employee information and allows users to perform various actions on employees.
 * @subcategory views/business/employees
 */

export default {
  data: () => ({
    companyId: "",
    userId: "",
    dialog: false,
    dialogDelete: false,
    dialogEdit: false,
    showField: true,
    headers: [
      { title: "Surname", key: "surname" },
      { title: "Name", key: "name" },
      { title: "User type", key: "type" },
      { title: "Status", key: "status" },
      { title: "Actions", key: "actions", sortable: false },
    ],
    headerProps: { class: "font-weight-bold" },
    itemProps: { class: "mx-auto" },
    employees: [],
    editedIndex: -1,
    editedItem: {
      name: "",
      surname: "",
      type: "",
      status: "",
      userId: "",
    },
    editType: {
        type: "",
    }
  }),

  mounted() {
    this.userId = localStorage.getItem("userId");
    this.$ApiService.find_user_by_id(this.userId).then((res) => {
      this.companyId = res.data.companyId;
      this.initialize_table();
    });
  },

  methods: {

     /**
     * Opens the delete confirmation dialog for the specified employee.
     * @param {Object} item - The employee item to be deleted.
     */
    deleteItem(item) {
      if (item.status !== "inactive") {
        this.editedIndex = this.employees.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialogDelete = true;
      }
    },

    /**
     * Confirms the deletion of an employee.
     * Deletes the employee item from the database and sends a confirmation email.
     */
    deleteItemConfirm() {
        this.$ApiService.delete_user(this.editedItem.userId).then(() => {

            this.$ApiService
                .find_user_by_id(this.editedItem.userId)
                .then((u) => {
                    const emailData = {
                    to: u.data.email,
                    subject: "Delete User Confirmed",
                    text: `${this.editedItem.name} ${this.editedItem.surname} has been successfully deleted yuor account.`,
                    };

                    this.$ApiService
                    .send_mail(emailData)
                    .then((emailRes) => {
                        console.log(emailRes.data);
                    })
                    .catch((emailError) => {
                        console.error("Error sending email:", emailError);
                    });

                    this.dialogDelete = false;
                    location.reload();
                })
                .catch((userError) => {
                    console.error("Error finding user:", userError);
                });

            this.$ApiService
                .find_user_by_id(this.userId)
                .then((u) => {
                    const emailData = {
                    to: u.data.email,
                    subject: "Delete User Confirmed",
                    text: `${u.data.name} ${u.data.surname} has been successfully deleted.`,
                    };

                    this.$ApiService
                    .send_mail(emailData)
                    .then((emailRes) => {
                        console.log(emailRes.data);
                    })
                    .catch((emailError) => {
                        console.error("Error sending email:", emailError);
                    });

                    this.dialogDelete = false;
                    location.reload();
                })
                .catch((userError) => {
                    console.error("Error finding user:", userError);
                });
            

            this.dialogDelete = false;
            location.reload();
        });
    },

    /**
     * Closes the delete confirmation dialog.
     */
    closeDelete() {
      this.dialogDelete = false;
    },

    /**
     * Opens the edit confirmation dialog for the specified employee.
     * @param {Object} item - The employee item to be edited.
     */
    editTypeItem(item) {
      if (item.status !== "inactive") {
        this.editedIndex = this.employees.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialogEdit = true;
      }
    },

    /**
     * Confirms the editing of an employee.
     * Edits the employee type in the database and sends a confirmation email.
     */
    editTypeItemConfirm() {
        this.editType.type = "BUSINESS";
        this.$ApiService.edit_user_type(this.editedItem.userId, this.editType).then((response) => {

            this.$ApiService
                .find_user_by_id(this.editedItem.userId)
                .then((u) => {
                    const emailData = {
                    to: u.data.email,
                    subject: "User Type Changed",
                    text: `Your account is now business.`,
                    };

                    this.$ApiService
                    .send_mail(emailData)
                    .then((emailRes) => {
                        console.log(emailRes.data);
                    })
                    .catch((emailError) => {
                        console.error("Error sending email:", emailError);
                    });

                    this.dialogDelete = false;
                    location.reload();
                })
                .catch((userError) => {
                    console.error("Error finding user:", userError);
                });

            this.dialogDelete = false;
            location.reload();
        });
    },

    /**
     * Closes the edit confirmation dialog.
     */
    closeEdit() {
      this.dialogEdit = false;
    },


    createBookingForEmployee(item){
      this.$router.push({ name: 'create_booking_for_employee', params: { employeeId: item.userId } });
    },

    /**
     * Initializes the table by fetching employee data and populating the employees array.
     */
    initialize_table() {
      console.log(this.companyId);
      this.$ApiService
        .get_list_employee(this.companyId)
        .then((response) => {
            const employees = response.data;
            console.log(employees);

            employees.forEach((employee) => {
              if(employee.id !== this.userId) {
                this.employees.push({
                    name: employee.name,
                    surname: employee.surname,
                    type: employee.type,
                    status: employee.status,
                    userId: employee.id,
                });
              }
            });

        })
        .catch((error) => {
          console.error(
            "Errore durante il recupero delle prenotazioni:",
            error,
          );
        });
    },
  },
};
</script>

<style>
.disabled {
  color: rgb(214, 214, 214);
  cursor: unset;
}

tbody tr:nth-of-type(even) {
  background-color: rgba(0, 0, 0, 0.05);
}
</style>