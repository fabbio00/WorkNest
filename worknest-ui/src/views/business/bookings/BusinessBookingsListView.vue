<template>
  <div class="mx-16">
    <v-data-table
      class="mx-auto my-16"
      :headers="headers"
      :items="bookings"
      :sort-by="[{ key: 'surname', order: 'asc' }]"
      style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)">
      <template v-slot:top>
        <v-toolbar flat class="bg-blue-grey-lighten-3">
          <v-toolbar-title>Bookings</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="searchName"
            label="Search Name"
            single-line
            hide-details
            class="search-field"
          >
            <template v-slot:append>
              <v-icon class="search-icon">mdi-magnify</v-icon>
            </template>
          </v-text-field>
          <v-text-field
            v-model="searchSurname"
            label="Search Surname"
            single-line
            hide-details
            class="search-field"
          >
            <template v-slot:append>
              <v-icon class="search-icon">mdi-magnify</v-icon>
            </template>
          </v-text-field>
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

      <template v-slot:item.status="{ item }">
        <v-chip :color="getStatusColor(item)">
          {{ item.status }}
        </v-chip>
      </template>
    </v-data-table>

    <v-dialog v-model="showStartDateDialog" persistent max-width="500px">
      <v-card class="dialog-card">
        <v-card-title class="headline">Select Start Date</v-card-title>
        <v-card-text>
          <v-date-picker v-model="tempStartDate"></v-date-picker>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="showStartDateDialog = false">Cancel</v-btn>
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
          <v-btn text color="primary" @click="showEndDateDialog = false">Cancel</v-btn>
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
      { title: "Surname", key: "surname" },
      { title: "Name", key: "name" },
      { title: "User type", key: "type" },
      { title: "Status", key: "status" },
      { title: "Date", key: "date" },
      { title: "Building", key: "building" },
      { title: "Floor", key: "floor" },
      { title: "Workstation", key: "workstationName" },
      { title: "Cost", key: "cost" },
    ],
    bookings: [],
    searchName: '',
    searchSurname: '',
    startDate: null,
    endDate: null,
    tempStartDate: null,
    tempEndDate: null,
    formattedStartDate: '',
    formattedEndDate: '',
    showStartDateDialog: false,
    showEndDateDialog: false,
  }),

  watch: {
    searchName: 'initialize_table',
    searchSurname: 'initialize_table',
  },

  mounted() {
    this.userId = localStorage.getItem("userId");
    this.fetchCompanyId()
      .then(() => {
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
      this.searchName = '';
      this.searchSurname = '';
      this.startDate = null;
      this.endDate = null;
      this.tempStartDate = null;
      this.tempEndDate = null;
      this.formattedStartDate = '';
      this.formattedEndDate = '';
      this.initialize_table();
    },

    /**
     * Fetches the company ID for the current user.
     * @returns {Promise} - Promise representing the completion of the fetch operation.
     */
    fetchCompanyId() {
      return this.$ApiService.find_user_by_id(this.userId)
        .then(response => {
          this.companyId = response.data.companyId;
        })
        .catch(error => {
          console.error("Error fetching company ID:", error);
        });
    },

    /**
     * Initializes the table by fetching booking data and populating the bookings array.
     */
    initialize_table() {
      this.$ApiService.get_list_by_company_booking(
        this.companyId, 
        this.searchName, 
        this.searchSurname, 
        this.formatDateForApi(this.startDate), 
        this.formatDateForApi(this.endDate)
      )
        .then(response => {
          const bookings = response.data;
          console.log(bookings);

          this.bookings = bookings.map((booking) => ({
            name: booking.userResource.name,
            surname: booking.userResource.surname,
            type: booking.userResource.type,
            status: booking.status,
            startDate: booking.startDate,
            userId: booking.userId,
            date: this.formatDate(booking.startDate),
            building: booking.buildingName,
            floor: booking.floorName,
            workstationName: booking.workStationName,
            cost: `${booking.workstationCostPerHour * 8}€`,
          }));
        })
        .catch(error => {
          console.error("Errore durante il recupero delle prenotazioni:", error);
        });
    },

    /**
     * Formats a date string to DD-MM-YYYY.
     * @param {string} dateString - The date string to format.
     * @returns {string} - The formatted date string.
     */
    formatDate(dateString) {
      if (!dateString) return '';
      const date = new Date(dateString);
      const day = String(date.getDate()).padStart(2, '0');
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const year = date.getFullYear();
      return `${day}-${month}-${year}`;
    },

    /**
     * Formats a date object to YYYY-MM-DD for API requests.
     * @param {Date} date - The date object to format.
     * @returns {string|null} - The formatted date string or null if no date is provided.
     */
    formatDateForApi(date) {
      if (!date) return null;
      const d = new Date(date);
      const month = '' + (d.getMonth() + 1);
      const day = '' + d.getDate();
      const year = d.getFullYear();

      return [year, month.padStart(2, '0'), day.padStart(2, '0')].join('-');
    },

    /**
     * Returns the color associated with the booking status.
     * @param {Object} item - The booking item.
     * @returns {string} - The color associated with the booking status.
     */
    getStatusColor(item) {
      const today = new Date().setHours(0, 0, 0, 0);
      const startDate = new Date(item.startDate).setHours(0, 0, 0, 0);
      if (item.status === 'canceled') {
        return 'red';
      } else if (item.status === 'active' && today > startDate) {
        return 'gray';
      } else {
        return 'green';
      }
    },
  }
}
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
</style>
