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
                        @click="editItemConfirm"
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
            @click="editItem(item)"
            :class="{
              disabled:
                item.status !== 'inactive' &&
                item.type !== 'Business'
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
            @click=""
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


export default {
  data: () => ({
    companyId: "356869f3-8402-4b65-b0e3-d8eb1f0de532",
    userId: "c0cae2ca-8085-4772-9237-e09e9c22cd6b",
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
    this.initialize_table();
  },

  methods: {

    deleteItem(item) {
      if (item.status !== "inactive") {
        this.editedIndex = this.employees.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialogDelete = true;
      }
    },


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

    closeDelete() {
      this.dialogDelete = false;
    },

    editItem(item) {
      if (item.status !== "inactive") {
        this.editedIndex = this.employees.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialogEdit = true;
      }
    },

    editItemConfirm() {
        this.editType.type = "Business";
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

    closeEdit() {
      this.dialogEdit = false;
    },


    initialize_table() {
      this.$ApiService
        .get_list_employee(this.companyId)
        .then((response) => {
            const employees = response.data;
            console.log(employees);

            employees.forEach((employee) => {
                this.employees.push({
                    name: employee.name,
                    surname: employee.surname,
                    type: employee.type,
                    status: employee.status,
                    userId: employee.id,
                });
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