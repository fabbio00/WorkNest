<template>
  <div class="mx-16">
    <v-data-table
      class="mx-auto my-16"
      :headers="headers"
      :items="bookings"
      :sort-by="[{ key: 'surname', order: 'asc' }]"
      :header-props="headerProps"
      :item-props="itemProps"
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
          <v-btn icon @click="clearFilters">
            <v-icon>mdi-trash-can</v-icon>
          </v-btn>
        </v-toolbar>
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
* - Displays a table of bookings with columns for surname, name, user type, status, date, building, floor, workstation, and cost.
* - Allows users to search and filter bookings by name and surname.
*
* Data properties:
* @vue-data {string} companyId - The ID of the company.
* @vue-data {string} userId - The ID of the current user.
* @vue-data {Array} employees - Array containing employee data.
* @vue-data {Array} bookings - Array containing booking data to be displayed in the table.
* @vue-data {string} searchName - String for searching bookings by name.
* @vue-data {string} searchSurname - String for searching bookings by surname.
* @vue-data {boolean} showField - Flag to control the visibility of a field.
* @vue-data {Array} headers - Array of objects representing table headers.
* @vue-data {Object} headerProps - Object containing properties for table header styling.
* @vue-data {Object} itemProps - Object containing properties for table item styling.
*
* Methods:
* @vue-method {Function} fetchCompanyId - Fetches the company ID based on the current user.
* @vue-method {Function} initialize_table - Initializes the table by fetching booking data and populating the bookings array.
* @vue-method {Function} formatDate - Formats a date string into a readable format.
* @vue-method {Function} clearFilters - Clears the search filters and refreshes the table data.
*
* Usage:
* This component is used within a Vue application to manage booking data and display it in a table format.
* It integrates with backend APIs to fetch booking information and allows users to perform various actions on bookings.
* @subcategory views/business/bookings
*/

export default {
data: () => ({
  companyId: null,
  userId: null,
  employees: [],
  bookings: [],
  searchName: '',
  searchSurname: '',
  showField: true,
  headers: [
    { title: "Surname", key: "surname" },
    { title: "Name", key: "name" },
    { title: "User type", key: "type" },
    { title: "Status", key: "status" },
    { title: "Date", key: "date"},
    { title: "Building", key: "building"},
    { title: "Floor", key: "floor"}, 
    { title: "Workstation", key: "workstationName"},
    { title: "Cost", key: "cost"},
  ],
  headerProps: { class: "font-weight-bold" },
  itemProps: { class: "mx-auto" },
}),

watch: {
  searchName: 'initialize_table',
  searchSurname: 'initialize_table'
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
   * Fetches the company ID based on the current user.
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
    this.$ApiService.get_list_by_company_booking(this.companyId, this.searchName, this.searchSurname)
      .then(response => {
        const bookings = response.data;
        console.log(bookings);

        this.bookings = bookings.map((booking) => ({
          name: booking.userResource.name, 
          surname: booking.userResource.surname,
          type: booking.userResource.type, 
          status: booking.status, 
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
   * Formats a date string into a readable format.
   * @param {string} dateString - The date string to be formatted.
   * @returns {string} - The formatted date string.
   */
  formatDate(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); 
    const year = date.getFullYear();
    return `${day}-${month}-${year}`;
  },

  /**
   * Clears the search filters and refreshes the table data.
   */
  clearFilters() {
    this.searchName = '';
    this.searchSurname = '';
    this.initialize_table();
  },
},
};
</script>

<style>
.search-field {
max-width: 200px;
}

.search-icon {
margin-left: -10px; /* Riduce lo spazio tra il campo di testo e l'icona */
}
</style>
