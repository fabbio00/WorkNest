<template>
  <div class="mx-16">
    <v-data-table
      class="mx-auto my-16"
      :headers="headers"
      :items="bookings"
      :sort-by="[{ key: 'id', order: 'asc' }]"
      style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)"
    >
      <template v-slot:top>
        <v-toolbar flat class="bg-blue-grey-lighten-3">
          <v-toolbar-title>Bookings</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="formattedStartDate"
            label="Start Date"
            single-line
            hide-details
            readonly
            class="date-field"
            @click="showStartDateDialog = true"
          >
            <template v-slot:append>
              <v-icon class="calendar-icon">mdi-calendar</v-icon>
            </template>
          </v-text-field>
          <v-text-field
            v-model="formattedEndDate"
            label="End Date"
            single-line
            hide-details
            readonly
            class="date-field"
            @click="showEndDateDialog = true"
          >
            <template v-slot:append>
              <v-icon class="calendar-icon">mdi-calendar</v-icon>
            </template>
          </v-text-field>
          <v-btn icon @click="clearFilters">
            <v-icon>mdi-trash-can</v-icon>
          </v-btn>
        </v-toolbar>
      </template>

      <template v-slot:header="{ props }">
        <tr>
          <th
            v-for="header in props.headers"
            :key="header.key"
            class="centered-header"
          >
            {{ header.title }}
          </th>
        </tr>
      </template>

      <template v-slot:item="{ item }">
        <tr>
          <td v-for="header in headers" :key="header.key" class="centered-cell">
            <span v-if="header.key !== 'action'">{{ item[header.key] }}</span>
            <span v-else>
              <v-btn
                icon
                @click="fetchAssociatedBookings(item.id)"
                class="custom-small-btn"
              >
                <v-icon>mdi-arrow-down-drop-circle</v-icon>
              </v-btn>
            </span>
          </td>
        </tr>
      </template>

      <template v-slot:item.status="{ item }">
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

      <template v-slot:item.expanded-row="{ item }">
        <div v-if="item.expanded">
          <v-data-table
            :headers="associatedHeaders"
            :items="item.associatedBookings"
            class="nested-table"
          ></v-data-table>
        </div>
      </template>
    </v-data-table>

    <transition
      enter-active-class="animate__animated animate__fadeIn"
      leave-active-class="animate__animated animate__zoomOut"
    >
      <v-data-table
        v-if="expanded"
        class="mx-auto my-16"
        :headers="associatedHeaders"
        :items="associatedBooking"
        :sort-by="[{ key: 'id', order: 'asc' }]"
        style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)"
      >
        <template v-slot:top>
          <v-toolbar flat class="bg-blue-grey-lighten-3">
            <v-toolbar-title>Associated Bookings</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-select
              v-model="searchType"
              :items="types"
              label="Type"
              class="type-select mt-5 mr-3"
              style="width: 200px"
              variant="underlined"
            ></v-select>
          </v-toolbar>
        </template>

        <template v-slot:item.status="{ item }">
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

        <template v-slot:item.actions="{ item }">
          <v-icon
            size="small"
            @click="deleteItem(item)"
            class="ml-3"
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

        <template v-slot:item.expanded-row="{ item }">
          <v-data-table
            :headers="associatedHeaders"
            :items="item.associatedBookings"
            class="nested-table"
          ></v-data-table>
        </template>
      </v-data-table>
    </transition>

    <v-dialog v-model="showStartDateDialog" persistent max-width="500px">
      <v-card class="dialog-card">
        <v-card-title class="headline">Select Start Date</v-card-title>
        <v-card-text>
          <v-date-picker v-model="tempStartDate"></v-date-picker>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="showStartDateDialog = false"
            >Cancel</v-btn
          >
          <v-btn text color="primary" @click="confirmStartDate">OK</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="showEndDateDialog" persistent max-width="500px">
      <v-card class="dialog-card">
        <v-card-title class="headline">Select End Date</v-card-title>
        <v-card-text>
          <v-date-picker v-model="tempEndDate"></v-date-picker>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="showEndDateDialog = false"
            >Cancel</v-btn
          >
          <v-btn text color="primary" @click="confirmEndDate">OK</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
/**
 * Vue component for managing bookings.
 * This component allows users to view and manage bookings, including displaying booking details in a table format.
 *
 * Features:
 * - Displays a table of bookings with columns for surname, name, user type, status, date, building, floor, workstation, and cost.
 * - Allows users to filter bookings by name, surname, and date range.
 * - Provides dialogs for selecting start and end dates for filtering.
 *
 * Data properties:
 * @vue-data {Array} headers - Array of objects representing table headers.
 * @vue-data {Array} bookings - Array containing booking data to be displayed in the table.
 * @vue-data {string} searchName - The name to filter bookings by.
 * @vue-data {string} searchSurname - The surname to filter bookings by.
 * @vue-data {string|null} startDate - The start date for filtering bookings.
 * @vue-data {string|null} endDate - The end date for filtering bookings.
 * @vue-data {string|null} tempStartDate - Temporary start date for the date picker dialog.
 * @vue-data {string|null} tempEndDate - Temporary end date for the date picker dialog.
 * @vue-data {string} formattedStartDate - Formatted start date for display.
 * @vue-data {string} formattedEndDate - Formatted end date for display.
 * @vue-data {boolean} showStartDateDialog - Flag to control the visibility of the start date picker dialog.
 * @vue-data {boolean} showEndDateDialog - Flag to control the visibility of the end date picker dialog.
 *
 * Methods:
 * @vue-method {Function} confirmStartDate - Confirms the selected start date and updates the bookings table.
 * @vue-method {Function} confirmEndDate - Confirms the selected end date and updates the bookings table.
 * @vue-method {Function} clearFilters - Clears all filters and resets the bookings table.
 * @vue-method {Function} fetchCompanyId - Fetches the company ID for the current user.
 * @vue-method {Function} initialize_table - Initializes the table by fetching booking data and populating the bookings array.
 * @vue-method {Function} formatDate - Formats a date string to DD-MM-YYYY.
 * @vue-method {Function} formatDateForApi - Formats a date object to YYYY-MM-DD for API requests.
 * @vue-method {Function} getStatusColor - Returns the color associated with the booking status.
 * @subcategory views/business/bookings
 */

export default {
  data: () => ({
    headers: [
      { title: "Building", key: "building" },
      { title: "Date", key: "day" },
      { title: "Time", key: "hours" },
      { title: "Number Of Bookings", key: "numberOfBookings" },
      { title: "Total Cost", key: "totalCost" },
      { title: "Actions", key: "action", sortable: false },
    ],
    associatedHeaders: [
      { title: "Date", key: "startDate" },
      { title: "Status", key: "status" },
      { title: "Workstation", key: "workStationName" },
      { title: "Name", key: "name"},
      { title: "Surname", key: "surname"},
      { title: "Type", key: "workStationType" },
      { title: "Cost", key: "workstationCostPerHour" },
      { title: "Building", key: "buildingName" },
      { title: "Floor", key: "floorName" },
      { title: "Actions", key: "actions" },
    ],
    types: ["desk", "meeting_room"],
    searchType: "desk",
    bookings: [],
    associatedBooking: [],
    selectedBookingsForDelete: [],
    expanded: false,
    keepExpanded: false,
    startDate: null,
    endDate: null,
    tempStartDate: null,
    tempEndDate: null,
    formattedStartDate: "",
    formattedEndDate: "",
    lastSelectedBookingId: null,
    lastSelectedSearchType: null,
    showStartDateDialog: false,
    showEndDateDialog: false,
  }),

  watch: {
    searchType: function () {
      if (this.lastSelectedBookingId) {
        this.fetchAssociatedBookings(this.lastSelectedBookingId);
      }
    },
  },

  mounted() {
    this.userId = localStorage.getItem("userId");
    this.fetchCompanyId().then(() => {
      this.initialize_table();
    });
  },

  methods: {
    /**
     * Confirms the selected start date and updates the bookings table.
     */
    confirmStartDate() {
      this.startDate = this.tempStartDate;
      this.formattedStartDate = this.formatDate(this.startDate);
      this.showStartDateDialog = false;
      this.initialize_table();
    },

    /**
     * Confirms the selected end date and updates the bookings table.
     */
    confirmEndDate() {
      this.endDate = this.tempEndDate;
      this.formattedEndDate = this.formatDate(this.endDate);
      this.showEndDateDialog = false;
      this.initialize_table();
    },

    /**
     * Clears all filters and resets the bookings table.
     */
    clearFilters() {
      this.searchName = "";
      this.searchSurname = "";
      this.startDate = null;
      this.endDate = null;
      this.tempStartDate = null;
      this.tempEndDate = null;
      this.formattedStartDate = "";
      this.formattedEndDate = "";
      this.searchType = "desk";
      this.initialize_table();
    },

    /**
     * Fetches the company ID for the current user.
     * @returns {Promise} - Promise representing the completion of the fetch operation.
     */
    fetchCompanyId() {
      return this.$ApiService
        .find_user_by_id(this.userId)
        .then((response) => {
          this.companyId = response.data.companyId;
        })
        .catch((error) => {
          console.error("Error fetching company ID:", error);
        });
    },

    /**
     * Deletes a booking if its status is not 'canceled' or 'gray'.
     * @param {Object} item - The booking item to be deleted.
     */
    deleteItem(item) {
      if (item.status !== "canceled" && item.status !== "gray") {
        this.$ApiService
          .delete_booking(item.bookingId)
          .then(() => {
            this.updateBookingsAfterDelete();
          })
          .catch((error) => {
            console.error("Error during booking status update:", error);
          });
      }
    },

    /**
     * Updates the bookings table after a deletion by re-fetching the booking data.
     * If the associated bookings table is expanded, it also refreshes the associated bookings.
     */
    updateBookingsAfterDelete() {
      // Re-fetch associated bookings after deletion to update the main table
      this.initialize_table();
      if (this.expanded) {
        this.fetchAssociatedBookings(this.lastSelectedBookingId, true);
      }
    },

    /**
     * Initializes the table by fetching booking data and populating the bookings array.
     */
    initialize_table() {
      this.$ApiService
        .get_booking_businesses_by_user_id(
          this.userId,
          this.formatDateForApi(this.startDate),
          this.formatDateForApi(this.endDate),
        )
        .then((response) => {
          const bookings = response.data;

          // Fetch associated bookings for each booking
          const bookingPromises = bookings.map((booking) =>
            this.$ApiService
              .get_bookings_by_business_booking_id(booking.id, "desk")
              .then((associatedResponseDesk) => {
                return this.$ApiService
                  .get_bookings_by_business_booking_id(
                    booking.id,
                    "meeting_room",
                  )
                  .then((associatedResponseMeetingRoom) => {
                    const associatedBookings = [
                      ...associatedResponseDesk.data,
                      ...associatedResponseMeetingRoom.data,
                    ];
                    const activeBookings = associatedBookings.filter(
                      (associated) => associated.status !== "canceled",
                    );
                    const numberOfBookings = activeBookings.length;
                    const totalCost =
                      activeBookings.reduce(
                        (sum, current) => sum + current.workstationCostPerHour,
                        0,
                      ) * 8;

                    return {
                      id: booking.id,
                      building: activeBookings[0]?.buildingName || "",
                      day: this.formatDate(booking.bookingDate),
                      hours: this.formatTime(booking.bookingDate),
                      numberOfBookings,
                      totalCost: `${totalCost} €`,
                      userId: booking.userId,
                      status: booking.status,
                      associatedBookings: activeBookings,
                    };
                  });
              }),
          );

          // Wait for all promises to resolve
          Promise.all(bookingPromises)
            .then((bookingResults) => {
              this.bookings = bookingResults.filter(
                (booking) => booking.associatedBookings.length > 0,
              );
            })
            .catch((error) => {
              console.error("Error fetching associated bookings:", error);
            });
        });
    },

    /**
     * Fetches associated bookings for a given booking ID and updates the associated bookings table.
     * Manages the expanded state of the associated bookings table.
     * @param {string} booking - The ID of the booking for which associated bookings are fetched.
     * @param {boolean} keepExpanded - Flag to determine if the associated bookings table should remain expanded.
     */
    fetchAssociatedBookings(booking, keepExpanded) {
      if (!keepExpanded) {
        if (
          booking === this.lastSelectedBookingId &&
          this.searchType === this.lastSelectedSearchType
        ) {
          this.expanded = !this.expanded;
        } else {
          this.expanded = true;
        }
      } else {
        this.expanded = true;
      }

      this.$ApiService
        .get_bookings_by_business_booking_id(booking, this.searchType)
        .then((response) => {
          this.associatedBooking = response.data.map((associatedBooking) => ({
            bookingId: associatedBooking.bookingId,
            startDate: this.formatDate(associatedBooking.startDate),
            status: associatedBooking.status,
            name: associatedBooking.userResource.name,
            surname: associatedBooking.userResource.surname,
            workStationName: associatedBooking.workStationName,
            workStationType: associatedBooking.workStationType,
            workstationCostPerHour: `${associatedBooking.workstationCostPerHour} €/hr`,
            buildingName: associatedBooking.buildingName,
            floorName: associatedBooking.floorName,
          }));
          console.log(this.associatedBooking);
          this.lastSelectedBookingId = booking;
          this.lastSelectedSearchType = this.searchType;
        })
        .catch((error) => {
          console.error("Error fetching associated bookings:", error);
        });
    },

    /**
     * Formats a date object to YYYY-MM-DD for API requests.
     * @param {Date} date - The date object to format.
     * @returns {string|null} - The formatted date string or null if no date is provided.
     */
    formatDateForApi(date) {
      if (!date) return null;
      const d = new Date(date);
      const month = "" + (d.getMonth() + 1);
      const day = "" + d.getDate();
      const year = d.getFullYear();

      return [year, month.padStart(2, "0"), day.padStart(2, "0")].join("-");
    },

    /**
     * Formats a date string to DD-MM-YYYY.
     * @param {string} dateString - The date string to format.
     * @returns {string} - The formatted date string.
     */
    formatDate(dateString) {
      if (!dateString) return "";
      const date = new Date(dateString);
      const day = String(date.getDate()).padStart(2, "0");
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const year = date.getFullYear();
      return `${month}-${day}-${year}`;
    },

    /**
     * Formats a date string into a time string (HH:MM).
     * @param {string} dateString - The date string to format.
     * @returns {string} - The formatted time string.
     */
    formatTime(dateString) {
      if (!dateString) return "";
      const date = new Date(dateString);
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      return `${hours}:${minutes}`;
    },

    /**
     * Formats a date-time string into a date and time string (DD-MM-YYYY HH:MM).
     * @param {string} dateTimeString - The date-time string to format.
     * @returns {string} - The formatted date and time string.
     */
    formatDateTime(dateTimeString) {
      if (!dateTimeString) return "";
      const date = new Date(dateTimeString);
      const day = String(date.getDate()).padStart(2, "0");
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const year = date.getFullYear();
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      return `${day}-${month}-${year} ${hours}:${minutes}`;
    },

    /**
     * Returns the color associated with the booking status.
     * @param {Object} item - The booking item.
     * @returns {string} - The color associated with the booking status.
     */
    getStatusColor(item) {
      const today = new Date().setHours(0, 0, 0, 0);
      const startDate = new Date(item.startDate).setHours(0, 0, 0, 0);
      if (item.status === "canceled") {
        return "red";
      } else if (item.status === "active" && today > startDate) {
        return "gray";
      } else {
        return "green";
      }
    },
  },
};
</script>

<style>
.search-field {
  max-width: 200px;
}

.search-icon {
  margin-left: -10px;
  vertical-align: middle;
}

.date-field {
  max-width: 150px;
  cursor: pointer;
}

.calendar-icon {
  cursor: pointer;
  margin-right: 10px;
  vertical-align: middle;
}

.dialog-card {
  width: 80% !important;
  max-width: none !important;
}

.nested-table {
  margin-left: 50px;
}

.v-btn.custom-small-btn {
  width: 30px;
  height: 30px;
  padding: 0;
}

.v-btn.custom-small-btn .v-btn__content {
  width: 30px;
  height: 30px;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.v-btn.custom-small-btn .v-icon {
  font-size: 20px;
}

.disabled {
  color: rgb(214, 214, 214);
  cursor: not-allowed;
  pointer-events: none;
}

tbody tr:nth-of-type(even) {
  background-color: rgba(0, 0, 0, 0.05);
}
</style>
