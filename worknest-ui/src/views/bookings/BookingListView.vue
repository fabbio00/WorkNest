<template>
  <div class="mx-16">
    <v-data-table
      class="mx-auto my-16"
      :headers="headers"
      :items="bookings"
      :sort-by="[{ key: 'startDate', order: 'asc' }]"
      :header-props="headerProps"
      :item-props="itemProps"
      style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)"
    >
      <template v-slot:top>
        <v-toolbar flat class="bg-blue-grey-lighten-3">
          <v-toolbar-title>My Bookings</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ props }">
              <v-btn
                class="mb-2"
                color="primary"
                dark
                v-bind="props"
                to="/booking"
              >
                Create new booking
              </v-btn>
            </template>
          </v-dialog>
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="text-h5"
                >Are you sure you want to delete this item?</v-card-title
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
        </v-toolbar>
      </template>
      <template #item.actions="{ item }">
        <v-icon
          class="me-2"
          size="small"
          @click="editItem(item)"
          :class="{
            disabled:
              item.status !== 'canceled' &&
              new Date(new Date().setHours(0, 0, 0, 0)) <=
                new Date(new Date(item.startDate).setHours(0, 0, 0, 0))
                ? false
                : true,
          }"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          size="small"
          @click="deleteItem(item)"
          :class="{
            disabled:
              item.status !== 'canceled' &&
              new Date(new Date().setHours(0, 0, 0, 0)) <=
                new Date(new Date(item.startDate).setHours(0, 0, 0, 0))
                ? false
                : true,
          }"
        >
          mdi-delete
        </v-icon>
      </template>

      <template #item.startDate="{ item }">
        <v-icon
          class="me-2"
          size="small"
          :color="
            item.status == 'canceled'
              ? 'red'
              : item.status == 'active' &&
                  new Date(new Date().setHours(0, 0, 0, 0)) >
                    new Date(new Date(item.startDate).setHours(0, 0, 0, 0))
                ? 'gray'
                : 'green'
          "
        >
          {{
            item.status == "canceled"
              ? "mdi-calendar-remove"
              : item.status == "active" &&
                  new Date(new Date().setHours(0, 0, 0, 0)) >
                    new Date(new Date(item.startDate).setHours(0, 0, 0, 0))
                ? "mdi-calendar-check"
                : "mdi-calendar-clock"
          }}
        </v-icon>
        {{ item.startDate }}
      </template>
      <template #item.status="{ item }">
        <v-chip
          :color="
            item.status == 'canceled'
              ? 'red'
              : item.status == 'active' &&
                  new Date(new Date().setHours(0, 0, 0, 0)) >
                    new Date(new Date(item.startDate).setHours(0, 0, 0, 0))
                ? 'gray'
                : 'green'
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
 * Vue component for managing bookings.
 * This component allows users to view and manage bookings, including displaying booking details in a table format.
 *
 * Features:
 * - Displays a table of bookings with columns for date, check-in time, check-out time, workstation, and status.
 * - Allows users to view booking details and perform actions such as editing or deleting bookings.
 *
 * Data properties:
 * @vue-data {string} userId - The ID of the current user.
 * @vue-data {boolean} dialog - Flag to control the visibility of the delete confirmation dialog.
 * @vue-data {boolean} dialogDelete - Flag to control the visibility of the delete confirmation dialog.
 * @vue-data {boolean} showField - Flag to control the visibility of a field.
 * @vue-data {Array} headers - Array of objects representing table headers.
 * @vue-data {Object} headerProps - Object containing properties for table header styling.
 * @vue-data {Object} itemProps - Object containing properties for table item styling.
 * @vue-data {Array} bookings - Array containing booking data to be displayed in the table.
 * @vue-data {number} editedIndex - Index of the currently edited booking.
 * @vue-data {Object} editedItem - Object representing the currently edited booking.
 * @vue-data {Object} defaultItem - Object representing the default booking item.
 *
 * Methods:
 * @vue-method {Function} deleteItem - Deletes a booking item.
 * @vue-method {Function} deleteItemConfirm - Confirms the deletion of a booking item.
 * @vue-method {Function} closeDelete - Closes the delete confirmation dialog.
 * @vue-method {Function} formatData - Formats the date string to display only the date part.
 * @vue-method {Function} initialize_table - Initializes the table by fetching booking data and populating the bookings array.
 *
 * Usage:
 * This component is used within a Vue application to manage booking data and display it in a table format.
 * It integrates with backend APIs to fetch booking information and allows users to perform various actions on bookings.
 * @subcategory views/bookings
 */

export default {
  data: () => ({
    userId: "",
    dialog: false,
    dialogDelete: false,
    showField: true,
    headers: [
      {
        title: "Date",
        align: "start",
        sortable: false,
        key: "startDate",
      },
      { title: "Check-in", key: "checkIn" },
      { title: "Check-out", key: "checkOut" },
      { title: "Workstation", key: "workStation" },
      { title: "Status", key: "status" },
      { title: "Actions", key: "actions", sortable: false },
    ],
    headerProps: { class: "font-weight-bold" },
    itemProps: { class: "mx-auto" },
    bookings: [],
    editedIndex: -1,
    editedItem: {
      startDate: "",
      checkIn: 0,
      checkOut: 0,
      workStation: 0,
      status: 0,
      bookingId: "",
    },
    defaultItem: {
      startDate: "",
      checkIn: 0,
      checkOut: 0,
      workStation: 0,
      status: 0,
    },
  }),

  mounted() {
    this.userId = localStorage.getItem("userId");
    this.initialize_table();
  },

  methods: {
    /**
     * Deletes a booking item.
     * Opens the delete confirmation dialog for the specified booking item.
     * @param {Object} item - The booking item to be deleted.
     */
    deleteItem(item) {
      if (item.status !== "canceled") {
        this.editedIndex = this.bookings.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialogDelete = true;
      }
    },

    /**
     * Confirms the deletion of a booking item.
     * Deletes the booking item from the database and reloads the page.
     */
    deleteItemConfirm() {
      this.$ApiService.delete_booking(this.editedItem.bookingId).then(() => {
        const userId = this.userId;
        const bookingDate = new Date(this.editedItem.startDate);
        const formattedDate = bookingDate.toLocaleDateString("en-US", {
          year: "numeric",
          month: "long",
          day: "numeric",
        });
        this.$ApiService
          .find_user_by_id(userId)
          .then((u) => {
            const emailData = {
              to: u.data.email,
              subject: "Delete Booking Confirmed",
              text: `Your booking for the ${this.editedItem.workStation} has been successfully deleted for ${formattedDate}.`,
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
      });
    },

    /**
     * Closes the delete confirmation dialog.
     */
    closeDelete() {
      this.dialogDelete = false;
    },
    /**
     * Redirects to the booking modification page.
     * If the booking item's status is not 'canceled', retrieves its booking ID
     * and navigates to the 'modify_booking' route to allow editing.
     *
     * @param {Object} item - The booking item to edit.
     */
    editItem(item) {
      if (item.status !== "canceled") {
        const bookingId = item.bookingId;
        this.$router.push({ name: "modify_booking", params: { bookingId } });
      }
    },

    /**
     * Formats the date string to display only the date part.
     *
     * @param {string} date - The date string to format.
     * @returns {string} The formatted date string containing only the date part.
     */
    formatData(date) {
      const rightDate = date.split("T")[0];
      return rightDate;
    },

    /**
     * Initializes the table by fetching booking data and populating the bookings array.
     * This method retrieves booking data from the backend API using the user ID stored in the component's data.
     * It then processes each booking, formatting the date and fetching additional details about the associated workstation.
     * Finally, it populates the bookings array with the formatted booking data, which is used to render the table.
     */
    initialize_table() {
      this.$ApiService
        .get_list_booking(this.userId)
        .then((response) => {
          const bookings = response.data;

          bookings.forEach((booking) => {
            let varCheckIn = booking.checkIn;
            let varCheckOut = booking.checkOut;
            if (booking.checkIn == null) {
              varCheckIn = "-";
              varCheckOut = "-";
            }

            this.$ApiService
              .find_desk_by_id(booking.workStationId)
              .then((workStation) => {
                this.bookings.push({
                  startDate: this.formatData(booking.startDate),
                  checkIn: varCheckIn,
                  checkOut: varCheckOut,
                  workStation: workStation.data.name,
                  status: booking.status,
                  bookingId: booking.bookingId,
                });
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
  pointer-events: none;
}

tbody tr:nth-of-type(even) {
  background-color: rgba(0, 0, 0, 0.05);
}
</style>
