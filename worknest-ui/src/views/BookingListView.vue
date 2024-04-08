<template>
    <div class="mx-16"> 
    <v-data-table
      class="mx-auto my-16"
      :headers="headers"
      :items="bookings"
      :sort-by="[{ key: 'startDate', order: 'asc' }]"
      :header-props="headerProps"
      :item-props="itemProps"
      style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);"
    >
      <template v-slot:top>
        <v-toolbar
          flat
        >
          <v-toolbar-title>My Bookings</v-toolbar-title>
          <v-divider
            class="mx-4"
            inset
            vertical
          ></v-divider>
          <v-spacer></v-spacer>
          <v-dialog
            v-model="dialog"
            max-width="500px"
          >
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
              <v-card-title class="text-h5">Are you sure you want to delete this item?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-darken-1" variant="text" @click="closeDelete">Cancel</v-btn>
                <v-btn color="blue-darken-1" variant="text" @click="deleteItemConfirm">OK</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon
          class="me-2"
          size="small"
          @click="editItem(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          size="small"
          @click="deleteItem(item)"
        >
          mdi-delete
        </v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn
          color="primary"
          @click="initialize"
        >
          Reset
        </v-btn>
      </template>
    </v-data-table>
    </div>
  </template>




<script>
  export default {
    data: () => ({
        userId: "",
        dialog: false,
        dialogDelete: false,
        headers: [
            {
            title: 'Date',
            align: 'start',
            sortable: false,
            key: 'startDate',
            headerClass: 'bold-header'
            },
            { title: 'Check-in', key: 'checkIn'},
            { title: 'Check-out', key: 'checkOut' },
            { title: 'Workstation', key: 'workStation' },
            { title: 'Status', key: 'status' },
            { title: 'Actions', key: 'actions', sortable: false },
        ],
        headerProps: { class: 'font-weight-bold' },
        itemProps: { class: 'mx-auto'},
        bookings: [],
        editedIndex: -1,
        editedItem: {
            name: '',
            calories: 0,
            fat: 0,
            carbs: 0,
            protein: 0,
        },
        defaultItem: {
            name: '',
            calories: 0,
            fat: 0,
            carbs: 0,
            protein: 0,
        },
        }),

    mounted(){
        this.userId = localStorage.getItem("userId")
        this.initialize_table()
    },

    methods: {

        formatData(date) {
            const rightDate = date.split('T')[0];
            return rightDate;
        },

        initialize_table() {
            this.$ApiService.get_list_booking(this.userId).then((response) => {
                const bookings = response.data;

                bookings.forEach(booking => {

                    let varCheckIn = booking.checkIn;
                    let varCheckOut = booking.checkOut;
                    if(booking.checkIn == null){
                        varCheckIn = "-"
                        varCheckOut = "-"
                    }

                    this.$ApiService.find_desk_by_id(booking.workStationId).then((workStation) => {

                        this.bookings.push({
                        startDate: this.formatData(booking.startDate),
                        checkIn: varCheckIn,
                        checkOut: varCheckOut,
                        workStation: workStation.data.name,
                        status: booking.status,
                        });
                    });
                });
            })
            .catch((error) => {
                console.error("Errore durante il recupero delle prenotazioni:", error);
            });
        },

    },
  }
</script>


<style>
.bold-header {
    font-weight: bold;
}

</style>