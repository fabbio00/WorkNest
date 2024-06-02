<script setup>
import Floor1B1 from "@/components/Building1/Floor1B1.vue";
import Floor2B1 from "@/components/Building1/Floor2B1.vue";
import Floor3B1 from "@/components/Building1/Floor3B1.vue";
import Floor1B2 from "@/components/Building2/Floor1B2.vue";
import Floor2B2 from "@/components/Building2/Floor2B2.vue";
import Floor3B2 from "@/components/Building2/Floor3B2.vue";
import Floor1B3 from "@/components/Building3/Floor1B3.vue";
import Floor2B3 from "@/components/Building3/Floor2B3.vue";
</script>
<template>
  <div class="text-center my-7">
    <img
      src="/worknest-logo.ico"
      class="d-inline-flex"
      style="max-height: 100px"
      align="center"
    />
    <p
      v-if="!bookingId"
      class="text-h3 d-inline font-italic ml-5"
      style="vertical-align: middle"
    >
      Book desks for your team
    </p>
    <p
      v-else
      class="text-h3 d-inline font-italic ml-5"
      style="vertical-align: middle"
    >
      Modify your booking
    </p>
  </div>
  <Transition
    enter-active-class="animate__animated animate__fadeIn"
    enter-leave-class="animate__animated animate__fadeOut"
    appear
  >
    <v-row justify="center" v-show="!isSvgVisible" class="mx-auto px-12">
      <v-col cols="12" md="8" lg="6">
        <v-card class="mb-4 d-block rounded-lg" elevation="2">
          <v-row>
            <v-col>
              <v-date-picker
                v-model="this.selectedDate"
                :min="new Date(new Date().setDate(new Date().getDate() - 1))"
                no-title
                scrollable
                class="ml-8"
              ></v-date-picker>
            </v-col>
            <v-col>
              <p class="text-h4 mt-12 pt-5" style="font-size: 32px !important">
                Select building
              </p>
              <v-select
                class="my-8 mr-5"
                label="Select"
                chips
                variant="outlined"
                :items="buildings"
                item-title="name"
                v-model="selectedBuilding"
                :hint="
                  selectedBuilding !== null
                    ? selectedBuilding.address +
                      ' ' +
                      selectedBuilding.streetNumber +
                      ', ' +
                      selectedBuilding.city +
                      ' (' +
                      selectedBuilding.province +
                      ')'
                    : ''
                "
                return-object
                persistent-hint
              >
                <template v-slot:label>
                  <v-card-text>
                    <v-icon>mdi-office-building</v-icon>
                    Select
                  </v-card-text>
                </template>
                <template v-slot:item="{ props, item }">
                  <v-list-item v-bind="props" id="prettySelect">
                    <v-list-item-content>
                      <v-list-item-title>
                        {{ item.raw.name }}
                      </v-list-item-title>
                      <v-list-item-subtitle class="d-flex align-center">
                        <v-icon class="mr-2">mdi-office-building-marker</v-icon>
                        <p>
                          {{ item.raw.address }} {{ item.raw.streetNumber }},
                          {{ item.raw.city }} ({{ item.raw.province }})
                        </p>
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-select>
            </v-col>
          </v-row>
          <Transition enter-active-class="animate__animated animate__flipInX">
            <v-alert
              v-if="chooseDeskAlertVisible"
              border="top"
              type="warning"
              class="mb-2 mx-auto"
              style="width: 60%"
            >
              Please choose a date and a building</v-alert
            >
          </Transition>
          <v-card-actions>
            <v-btn
              color="primary"
              class="mx-auto rounded-pill"
              @click="findOccupiedDesks()"
              >Find Available Desks</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </Transition>

  <v-row scrollable class="ml-12">
    <v-col cols="12" md="3" class="my-12">
      <Transition
        enter-active-class="animate__animated animate__backInLeft"
        leave-active-class="animate__animated animate__backOutLeft"
      >
        <v-card
          v-if="isSvgVisible"
          elevation="5"
          class="rounded-lg mx-auto"
          style="width: 95%"
        >
          <v-row class="d-flex flex-column align-center my-auto">
            <v-col cols="12" class="text-center">
              <p class="text-h5">Select Floor</p>
            </v-col>
            <v-col cols="12" md="8">
              <v-select
                v-model="selectedFloor"
                :items="selectedBuilding.floors"
                class="my-auto mx-2"
                label="Floor"
                item-title="numberOfFloor"
                item-value="numberOfFloor"
                variant="outlined"
                chips
                @update:modelValue="deskDetails = null"
              >
                <template v-slot:label>
                  <v-card-text>
                    <v-icon>mdi-floor-plan</v-icon>
                    Floor
                  </v-card-text>
                </template>
                <template v-slot:item="{ props, item }">
                  <v-list-item v-bind="props" id="prettySelect">
                    <v-list-item-content>
                      <v-list-item-title>
                        <v-icon class="mr-2"
                          >mdi-home-floor-{{ item.raw.numberOfFloor }}</v-icon
                        >Floor {{ item.raw.numberOfFloor }}
                      </v-list-item-title>
                      <v-list-item-subtitle class="d-flex align-center">
                        <v-icon class="ml-4 mr-2"
                          >mdi-information-outline</v-icon
                        >
                        <p>
                          Bathrooms: {{ item.raw.numBathrooms }}, Workstations:
                          {{ item.raw.numOfDesks }}, Meeting Rooms:
                          {{ item.raw.numOfMeetingRooms }}
                        </p>
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-select>
            </v-col>
            <v-col cols="12" class="text-center">
              <p class="text-h5">Select Equimpent</p>
            </v-col>
            <v-col cols="12" md="8">
              <v-select
                v-model="selectedEquipment"
                :items="equipmentItems"
                class="my-auto mx-2"
                label="Equipment"
                variant="outlined"
                clearable
                chips
              >
                <template v-slot:label>
                  <v-card-text>
                    <v-icon>mdi-desk</v-icon>
                    Equipment
                  </v-card-text>
                </template>
                <template v-slot:item="{ props, item }">
                  <v-list-item v-bind="props" id="prettySelect">
                    <v-list-item-content>
                      <v-list-item-title>
                        {{ item.raw.title }}
                      </v-list-item-title>
                      <v-list-item-subtitle class="d-flex align-center">
                        <v-icon
                          v-for="(equip, index) in item.value.split(', ')"
                          :key="index"
                          class="mr-2"
                          >{{ getEquipmentIcon(equip) }}</v-icon
                        >
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-select>
            </v-col>
            <v-col cols="12" md="8" class="d-flex align-center justify-center">
              <Transition
                enter-active-class="animate__animated animate__fadeIn"
                leave-active-class="animate__animated animate__fadeOut"
              >
                <div v-if="wantsWindow != null" class="d-flex align-center">
                  <v-icon
                    @click="wantsWindow = null"
                    color="red"
                    style="position: absolute"
                    >mdi-close-circle</v-icon
                  >
                </div>
              </Transition>
              <v-checkbox
                v-model="wantsWindow"
                label="I want a window"
                class="ml-6 d-flex align-center"
              ></v-checkbox>
            </v-col>
          </v-row>
        </v-card>
      </Transition>
      <Transition
        enter-active-class="animate__animated animate__backInUp"
        leave-active-class="animate__animated animate__backOutDown"
      >
        <v-btn
          size="x-large"
          icon="mdi-calendar"
          class="d-flex position-fixed ml-auto"
          color="blue-grey-lighten-4"
          style="bottom: 2%; right: 2%"
          @click="
            isSvgVisible = false;
            selectedFloor = 1;
            selectedEquipment = null;
            wantsWindow = null;
            deskDetails = null;
          "
          v-if="isSvgVisible"
          elevation="12"
        >
        </v-btn>
      </Transition>
    </v-col>
    <v-col cols="12" md="6" class="my-12">
      <div class="svg-container" style="text-align: center">
        <div v-if="isSvgVisible && selectedBuilding.name == 'Building1'">
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor1B1
              v-if="selectedFloor == 1"
              :floorInfo="selectedBuilding.floors[0]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor2B1
              v-if="selectedFloor == 2"
              :floorInfo="selectedBuilding.floors[1]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor3B1
              v-if="selectedFloor == 3"
              :floorInfo="selectedBuilding.floors[2]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
        </div>
        <div v-else-if="isSvgVisible && selectedBuilding.name == 'Building2'">
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor1B2
              v-if="selectedFloor == 1"
              :floorInfo="selectedBuilding.floors[0]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor2B2
              v-if="selectedFloor == 2"
              :floorInfo="selectedBuilding.floors[1]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor3B2
              v-if="selectedFloor == 3"
              :floorInfo="selectedBuilding.floors[2]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
        </div>
        <div v-else-if="isSvgVisible && selectedBuilding.name == 'Building3'">
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor1B3
              v-if="selectedFloor == 1"
              :floorInfo="selectedBuilding.floors[0]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
          <Transition
            enter-active-class="animate__animated animate__fadeIn"
            appear
          >
            <Floor2B3
              v-if="selectedFloor == 2"
              :floorInfo="selectedBuilding.floors[1]"
              :occupiedDesks="occupiedDesks"
              :userType="userType"
              :equipment="selectedEquipment"
              :hasWindow="wantsWindow"
              @deskClicked="bookingDesk"
              style="background-color: #37474f40"
            />
          </Transition>
        </div>
      </div>
    </v-col>
    <v-col
      cols="12"
      md="5"
      class="my-12 mx-auto"
      justify="center"
      style="max-width: 400px"
    >
      <Transition enter-active-class="animate__animated animate__fadeIn">
        <div v-if="deskDetails && isSvgVisible" class="desk-details">
          <v-card
            elevation="5"
            class="rounded-lg"
            v-if="deskDetails.type !== 'meeting_room'"
          >
            <v-card-title class="headline">Desk Details</v-card-title>
            <v-card-text>
              <v-list>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Name desk:</strong>
                      {{ deskDetails.name }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Type:</strong>
                      {{ deskDetails.type }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item
                  v-if="
                    deskDetails.equipment && deskDetails.equipment !== 'null'
                  "
                >
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Equipment:</strong>
                      {{ deskDetails.equipment }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Floor:</strong>
                      {{ selectedFloor }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Price:</strong>
                      {{ deskDetails.pricePerH * 8 }}€</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <p>Assign to:<br /><br /></p>
                  <v-select
                    :items="employeeList"
                    item-value="user.id"
                    item-title="fullName"
                    label="User"
                    v-model="booking.userId"
                    :disabled="isAddClicked"
                    variant="outlined"
                  >
                  </v-select>
                </v-list-item>
              </v-list>
            </v-card-text>
            <v-card-actions>
              <v-btn
                color="primary"
                @click="assignBookingToUser"
                :disabled="!booking.userId"
                >Add</v-btn
              >
            </v-card-actions>
          </v-card>
          <v-card elevation="5" v-else>
            <v-card-title class="headline">Meeting Room Details</v-card-title>
            <v-card-text>
              <v-list>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Name room:</strong>
                      {{ deskDetails.name }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Number of seats:</strong>
                      {{ deskDetails.numberOfSeats }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Floor:</strong>
                      {{ selectedFloor }}</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title
                      ><strong>Price:</strong>
                      {{ deskDetails.pricePerH * 8 }}€</v-list-item-title
                    >
                  </v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <p>Assign to:<br /><br /></p>
                  <v-select
                    class="mt-4"
                    clearable
                    label="Select"
                    :items="employeeList"
                    item-value="user"
                    item-title="fullName"
                    v-model="meetingRoomList"
                    chips
                    multiple
                    variant="outlined"
                    :disabled="
                      isAddClicked ||
                      (savedUsersMeetingRoomList.find(
                        (desk) => desk.meeting_room_name === deskDetails.name,
                      ) &&
                        savedUsersMeetingRoomList.find(
                          (desk) => desk.meeting_room_name === deskDetails.name,
                        ).users.length >= deskDetails.numberOfSeats)
                    "
                  >
                  </v-select>
                </v-list-item>
              </v-list>
            </v-card-text>
            <v-card-actions>
              <v-btn
                color="primary"
                @click="assignMeetingRoomToUsers"
                :disabled="meetingRoomList.length == 0 || isAddClicked"
                >Add</v-btn
              >
            </v-card-actions>
          </v-card>
        </div>
      </Transition>
    </v-col>
    <v-fade-transition hide-on-leave>
      <v-dialog
        v-model="alertVisible"
        persistent
        min-width="500"
        max-width="800"
      >
        <v-card
          class="mx-auto booking-alert"
          elevation="16"
          min-width="500"
          max-width="800"
          :title="
            alertType === 'success'
              ? 'Confirmed booking'
              : alertType === 'warning'
                ? 'Warning!'
                : 'Error booking'
          "
          :text="alertText"
          style="white-space: pre-line"
        >
          <v-divider v-if="alertType !== 'warning'"></v-divider>

          <div class="py-12 text-center" v-if="alertType !== 'warning'">
            <v-icon
              class="mb-6"
              size="100"
              :color="alertType === 'success' ? 'success' : 'error'"
            >
              {{
                alertType === "success"
                  ? "mdi-check-circle-outline"
                  : "mdi-alert-outline"
              }}
            </v-icon>
          </div>

          <v-divider></v-divider>

          <div class="pa-4 text-end">
            <v-btn
              class="text-none"
              color="medium-emphasis"
              min-width="92"
              variant="outlined"
              rounded
              @click="goToRecap"
            >
              Close
            </v-btn>
          </div>
        </v-card>
      </v-dialog>
    </v-fade-transition>
  </v-row>

  <v-row class="d-flex justify-center align-center">
    <div class="mx-16" v-if="isSvgVisible && bookingList.length > 0">
      <v-data-table
        class="mx-auto my-16"
        :headers="headers"
        :items="bookingList"
        style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)"
      >
        <template v-slot:top>
          <v-toolbar flat class="bg-blue-grey-lighten-3">
            <v-toolbar-title>Bookings</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="500px">
              <template v-slot:activator>
                <v-btn class="mb-2" color="primary" dark @click="createBooking">
                  Confirm booking
                </v-btn>
              </template>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.actions="{ item }">
          <v-icon
            size="small"
            @click="deleteItem(item)"
            :class="{
              disabled: item.status !== 'inactive' ? false : true,
            }"
          >
            mdi-delete
          </v-icon>
        </template>
      </v-data-table>
    </div>
  </v-row>
</template>

<script>
/**
 * Vue component for managing desk and meeting room bookings.
 * This component allows users to view available desks, select a desk or meeting room for booking,
 * and create new bookings for individual employees or groups.
 *
 * Features:
 * - Displays a date picker to select the booking start date.
 * - Highlights available desks and marks occupied desks.
 * - Allows users to select a desk and view its details before booking.
 * - Submits booking details to create new bookings.
 * - Manages bookings for meeting rooms with multiple users.
 * - Shows alerts for booking conflicts or errors.
 *
 * Data properties:
 * @vue-data {boolean} isSvgVisible - Flag to determine SVG visibility.
 * @vue-data {boolean} isAddClicked - Flag to determine if the 'Add' button has been clicked.
 * @vue-data {Array} savedUsersMeetingRoomList - List of users already assigned to the selected meeting room.
 * @vue-data {Array} meetingRoomList - List of users to be assigned to the selected meeting room.
 * @vue-data {Array} selectedEmployees - List of employees selected for booking.
 * @vue-data {Array} employeeWithBooking - List of employees with existing bookings.
 * @vue-data {Array} employeeList - List of employees available for booking.
 * @vue-data {Array} bookingList - List of bookings created in the current session.
 * @vue-data {boolean} dataLoaded - Flag to determine if the data has been fully loaded.
 * @vue-data {string} companyId - ID of the user's company.
 * @vue-data {string} userId - ID of the current user.
 * @vue-data {Date|null} selectedDate - The date selected for booking.
 * @vue-data {Object} booking - Contains details for the booking, including startDate, endDate, status, hasPenalty, workStationId, and userId.
 * @vue-data {Array} headers - Column headers for displaying employee bookings in a table.
 * @vue-data {Object|null} deskDetails - Details of the selected desk.
 * @vue-data {boolean} alertVisible - Flag to control the visibility of alerts.
 * @vue-data {string} alertText - Text content for the alert.
 * @vue-data {string} alertType - Type of alert (e.g., "success", "error").
 *
 * Methods:
 * @vue-method {Function} formatDate - Formats a date to 'YYYY-MM-DD' format.
 * @vue-method {Function} findOccupiedDesks - Finds available desks for booking and updates the UI.
 * @vue-method {Function} bookingDesk - Handles the event when a user selects a desk for booking.
 * @vue-method {Function} createBooking - Creates a new booking with the provided details.
 * @vue-method {Function} goToRecap - Redirects the user to the booking list page after successful booking.
 * @vue-method {Function} assignBookingToUser - Assigns a booking to a user and updates the booking list.
 * @vue-method {Function} assignMeetingRoomToUsers - Assigns a meeting room to multiple users and updates the booking list.
 * @vue-method {Function} deleteItem - Deletes a booking from the booking list.
 *
 * Usage:
 * This component is used within a Vue application to manage desk and meeting room booking functionality.
 * It integrates with backend APIs to retrieve desk availability, create new bookings, and manage bookings for multiple users.
 * @subcategory views/company
 */

export default {
  props: {
    bookingId: {
      type: String,
    },
  },

  data() {
    return {
      isSvgVisible: false,
      isAddClicked: false,
      savedUsersMeetingRoomList: [],
      meetingRoomList: [],
      selectedEmployees: [],
      employeeWithBooking: [],
      employeeList: [],
      bookingList: [],
      dataLoaded: false,
      companyId: "",
      userId: "",
      selectedDate: null,
      booking: {
        startDate: "",
        endDate: "",
        status: "",
        hasPenalty: false,
        workStationId: "",
        userId: "",
      },

      headers: [
        { title: "Surname", key: "user.surname" },
        { title: "Name", key: "user.name" },
        { title: "User type", key: "user.type" },
        { title: "WorkStation", key: "workstation.name" },
        { title: "Actions", key: "actions", sortable: false },
      ],

      deskDetails: null,
      alertVisible: false,
      alertText: "",
      alertType: "success",

      buildings: [],
      selectedBuilding: null,
      chooseDeskAlertVisible: false,
      occupiedDesks: [],
      userType: "",
      equipmentItems: [
        {
          title: "Monitor",
          value: "monitor",
          icon: "mdi-monitor",
        },
        {
          title: "Monitor + Keyboard",
          value: "monitor, keyboard",
          icon: "mdi-keyboard",
        },
        {
          title: "Monitor + Keyboard + Mouse",
          value: "monitor, keyboard, mouse",
          icon: "mdi-mouse",
        },
      ],
      selectedFloor: 1,
      selectedEquipment: null,
      wantsWindow: null,
    };
  },

  mounted() {
    this.userId = localStorage.getItem("userId");
    this.$ApiService.find_user_by_id(this.userId).then((res) => {
      this.userType = res.data.type;
      this.companyId = res.data.companyId;
    });
    this.getBuildings();
  },

  methods: {
    /**
     * Formats date to 'YYYY-MM-DD' format.
     * This method takes a Date object and returns a string representation of the date
     * in the format 'YYYY-MM-DD'. This format is commonly used for sending dates to APIs
     * or displaying them in input fields.
     * @param {Date} date - The date to format.
     * @returns {string} - The formatted date string.
     */
    formatDate(date) {
      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, "0");
      const day = date.getDate().toString().padStart(2, "0");

      return `${year}-${month}-${day}`;
    },

    /**
     * Finds available desks for booking.
     * This method retrieves the list of available desks for booking based on the selected start date.
     * It queries the backend API to check for desk availability and updates the UI accordingly.
     * Additionally, it highlights desks that are unavailable, such as those already booked or restricted
     * based on user permissions.
     */
    findOccupiedDesks() {
      if (this.selectedDate !== null && this.selectedBuilding !== null) {
        this.deskDetails = null;
        this.chooseDeskAlertVisible = false;
        this.bookingList = [];
        this.employeeWithBooking = [];
        this.employeeList = [];
        const date = this.formatDate(this.selectedDate);

        this.$ApiService.find_occupied_desks(date).then((occupiedDesks) => {
          this.occupiedDesks = occupiedDesks.data
            .filter((ws) => ws.status != "canceled")
            .map((ws) => ws.workStationId);
          this.isSvgVisible = true;

          occupiedDesks.data.forEach((wsOccupied) => {
            if (
              wsOccupied.status !== "canceled" &&
              wsOccupied.user.company.id == this.companyId
            ) {
              this.employeeWithBooking.push({
                user: wsOccupied.user,
              });
            }
          });
          this.dataLoaded = true;
          if (this.employeeWithBooking.length > 0) {
            this.alertVisible = true;
            this.alertType = "warning";
            let employeeNames = this.employeeWithBooking
              .map(
                (employee) =>
                  `• ${employee.user.name} ${employee.user.surname}${employee.user.id === this.userId ? " (You)" : ""}`,
              )
              .join("\n");
            this.alertText =
              "It is not possible to reserve a desk for the following users because they alredy have a booking for the current day:\n\n" +
              employeeNames;
          }
        });
      } else {
        this.chooseDeskAlertVisible = true;
      }
    },

    /**
     * Handles desk booking event.
     * This method is triggered when a user clicks on a desk to book it.
     * It retrieves the details of the selected desk from the backend and displays them to the user,
     * allowing them to review the desk information before confirming the booking.
     * @param {Event} event - The click event triggered when selecting a desk.
     */
    bookingDesk(deskId) {
      this.isAddClicked = false;
      const workStationId = deskId;
      this.employeeList = [];

      this.$ApiService
        .find_desk_by_id(workStationId)
        .then((res) => {
          this.deskDetails = res.data;
          this.booking.workStationId = workStationId;
        })
        .catch((deskError) => {
          console.error("Error finding desk:", deskError);
        });

      this.$ApiService.get_list_employee(this.companyId).then((res) => {
        res.data.forEach((employee) => {
          if (
            employee.status !== "inactive" &&
            !this.employeeWithBooking.some((e) => e.user.id === employee.id)
          ) {
            if (employee.id != this.userId) {
              this.employeeList.push({
                user: employee,
                fullName: `${employee.surname} ${employee.name}`,
              });
            } else {
              this.employeeList.push({
                user: employee,
                fullName: `${employee.surname} ${employee.name} (You)`,
              });
            }
          }
        });
      });
    },

    /**
     * Creates a new booking.
     * This method is called when the user confirms the booking after selecting a desk.
     * It sends the booking details to the backend API to create a new booking record.
     * Upon successful booking creation, it displays a success message to the user.
     * If an error occurs during the booking process, an error message is displayed.
     */
    createBooking() {
      let bookingListOnly = this.bookingList.map((item) => item.booking);
      let mailingList = this.bookingList.map((item) => item.user.email);
      let bookingBusiness = {
        bookingDate: this.booking.startDate,
        userId: this.userId,
      };

      let bookingBusinessId = "";

      this.$ApiService
        .create_booking_business(bookingBusiness)
        .then((res) => {
          bookingBusinessId = res.data.bookingBusinessId;
          bookingListOnly.forEach((booking) => {
            booking.bookingBusinessId = bookingBusinessId;
          });

          this.$ApiService
            .save_booking_list(bookingListOnly)
            .then((res) => {
              console.log(res);
              this.alertType = "success";
              this.alertText = "Booking confirmed successfully!";
              this.alertVisible = true;

              bookingListOnly.forEach((booking) => {
                const deskElement = document.querySelector(
                  `[data-id="${booking.workStationId}"]`,
                );
                if (deskElement) {
                  deskElement.setAttribute("fill", "red");
                  deskElement.style.pointerEvents = "none";
                }
              });

              let messages = this.bookingList.map((item) => {
                return `${item.user.name} ${item.user.surname} -> ${item.workstation.name}`;
              });
              let messageString = messages.join("\n");
              let bookingDate = this.formatDate(this.selectedDate);
              console.log(bookingDate);

              const emailData = {
                to: mailingList,
                subject: "New booking",
                text:
                  `A reservation has been created for your team for ${bookingDate}.\n Below is the list of users with the available desk: \n` +
                  messageString,
              };
              console.log(emailData);
              this.$ApiService
                .send_mail_list(emailData)
                .then((res) => {
                  console.log(res);
                })
                .catch((error) => {
                  console.log(error);
                });
            })
            .catch((error) => {
              this.alertVisible = true;
              this.alertType = "error";
              this.alertText = "Something went wrong, please try again!";
              console.log(error);
            });
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /**
     * Redirects to the booking recap page.
     * This method is called after a successful booking creation. It hides the alert and redirects the user to the booking list page.
     */
    goToRecap() {
      this.alertVisible = false;
      if (this.alertType == "success") {
        this.$router.push("/businessBookingsList");
      }
    },

    /**
     * Assigns a booking to a user.
     * This method assigns the booking details to the selected user and updates the booking list.
     * It ensures that the desk is marked as booked and prevents further selections until the booking is modified or canceled.
     */
    assignBookingToUser() {
      this.isAddClicked = true;

      let date = new Date(this.selectedDate);
      date.setDate(date.getDate() + 1);
      let dateToSave = date.toISOString().replace("Z", "+00:00");
      const regexTime = /\d{2}:\d{2}:\d{2}\.\d{3}/;
      const newData = dateToSave.replace(regexTime, "00:00:00.000");

      this.booking.startDate = newData;
      this.booking.endDate = this.booking.startDate;
      this.booking.status = "active";

      console.log(this.booking.startDate);

      let desk = document.querySelector(
        `[data-id="${this.booking.workStationId}"]`,
      );
      if (desk) {
        desk.setAttribute("fill", "gray");
        desk.style.pointerEvents = "auto";
        desk.style.cursor = "pointer";
      }

      const newBooking = Object.assign({}, this.booking);
      const index = this.bookingList.findIndex(
        (booking) => booking.booking.workStationId === newBooking.workStationId,
      );
      let removedBooking;
      if (index !== -1) {
        removedBooking = this.bookingList.splice(index, 1);
      }

      if (removedBooking) {
        this.employeeWithBooking = this.employeeWithBooking.filter(
          (employee) => employee.user.id !== removedBooking[0].booking.userId,
        );
      }

      this.$ApiService.find_user_by_id(this.booking.userId).then((u) => {
        this.employeeWithBooking.push({
          user: u.data,
        });
        this.bookingList.push({
          booking: newBooking,
          user: u.data,
          workstation: this.deskDetails,
        });
      });

      this.booking.userId = null;
    },

    /**
     * Assigns a meeting room to multiple users.
     * This method assigns the selected meeting room to multiple users and updates the booking list accordingly.
     * It checks if the number of users does not exceed the number of available seats in the meeting room.
     * If there are too many users selected, it displays an error message.
     */
    assignMeetingRoomToUsers() {
      let users = this.savedUsersMeetingRoomList.find(
        (room) => room.meeting_room_name === this.deskDetails.name,
      )
        ? this.savedUsersMeetingRoomList.find(
            (room) => room.meeting_room_name === this.deskDetails.name,
          ).users
        : [];
      let usersInMeetingRoom = users.length + this.meetingRoomList.length;
      console.log(users.length, this.meetingRoomList.length);
      if (usersInMeetingRoom > this.deskDetails.numberOfSeats) {
        this.alertVisible = true;
        this.alertType = "error";
        this.alertText =
          "You have selected more users than the number of seats available!";
        return;
      } else {
        this.isAddClicked = true;

        let date = new Date(this.selectedDate);
        date.setDate(date.getDate() + 1);
        let dateToSave = date.toISOString().replace("Z", "+00:00");
        const regexTime = /\d{2}:\d{2}:\d{2}\.\d{3}/;
        const newData = dateToSave.replace(regexTime, "00:00:00.000");

        this.booking.startDate = newData;
        this.booking.endDate = this.booking.startDate;
        this.booking.status = "active";

        let desk = document.querySelector(
          `[data-id="${this.booking.workStationId}"]`,
        );
        if (desk) {
          desk.setAttribute("fill", "gray");
          desk.style.pointerEvents = "auto";
          desk.style.cursor = "pointer";
        }

        this.meetingRoomList.forEach((user) => {
          const newBooking = Object.assign({}, this.booking);
          newBooking.userId = user.id;
          const index = this.bookingList.findIndex(
            (booking) => booking.user.id === newBooking.userId,
          );
          let removedBooking;
          if (index !== -1) {
            removedBooking = this.bookingList.splice(index, 1);
          }

          if (removedBooking) {
            this.employeeWithBooking = this.employeeWithBooking.filter(
              (employee) =>
                employee.user.id !== removedBooking[0].booking.userId,
            );
          }

          this.$ApiService.find_user_by_id(user.id).then((u) => {
            this.employeeWithBooking.push({
              user: u.data,
            });
            this.bookingList.push({
              booking: newBooking,
              user: u.data,
              workstation: this.deskDetails,
            });
          });
        });
        //this.savedUsersMeetingRoomList = this.savedUsersMeetingRoomList.concat(this.meetingRoomList.filter(user => !this.savedUsersMeetingRoomList.some(u => u.id === user.id)));
        let room = this.savedUsersMeetingRoomList.find(
          (room) => room.meeting_room_name === this.deskDetails.name,
        );
        if (room) {
          room.users.push(...this.meetingRoomList);
        } else {
          this.savedUsersMeetingRoomList.push({
            meeting_room_name: this.deskDetails.name,
            users: this.meetingRoomList,
          });
        }

        this.meetingRoomList = [];
      }
    },

    /**
     * Deletes a booking.
     * This method removes the selected booking from the booking list and updates the list of employees with existing bookings.
     * It also updates the UI to mark the desk as available if there are no more bookings for it.
     * @param {Object} item - The booking item to delete.
     */
    deleteItem(item) {
      let deskName = item.workstation.name;
      let usersInMeetingRoom = [];
      this.employeeList = [];

      let index = this.bookingList.findIndex(
        (booking) => booking.user.id === item.user.id,
      );
      this.bookingList.splice(index, 1);

      index = this.employeeWithBooking.findIndex(
        (employee) => employee.user.id === item.booking.userId,
      );
      this.employeeWithBooking.splice(index, 1);

      if (item.workstation.type === "meeting_room") {
        index = this.meetingRoomList.findIndex(
          (user) => user.id === item.booking.userId,
        );
        this.meetingRoomList.splice(index, 1);

        usersInMeetingRoom = this.savedUsersMeetingRoomList.find(
          (room) => room.meeting_room_name === deskName,
        ).users;
        index = usersInMeetingRoom.findIndex(
          (user) => user.id === item.booking.userId,
        );
        usersInMeetingRoom.splice(index, 1);
        let room = this.savedUsersMeetingRoomList.find(
          (room) => room.meeting_room_name === deskName,
        );
        if (room) {
          room.users = usersInMeetingRoom;
        }
      }

      let desk = document.querySelector(
        `[data-id="${item.booking.workStationId}"]`,
      );
      if (
        desk &&
        item.workstation.type === "meeting_room" &&
        usersInMeetingRoom.length === 0
      ) {
        desk.setAttribute("fill", "green");
        desk.style.pointerEvents = "auto";
        desk.style.cursor = "pointer";
      } else if (desk && item.workstation.type !== "meeting_room") {
        desk.setAttribute("fill", "green");
        desk.style.pointerEvents = "auto";
        desk.style.cursor = "pointer";
      }

      this.$ApiService.get_list_employee(this.companyId).then((res) => {
        res.data.forEach((employee) => {
          if (
            employee.status !== "inactive" &&
            !this.employeeWithBooking.some((e) => e.user.id === employee.id)
          ) {
            if (employee.id != this.userId) {
              this.employeeList.push({
                user: employee,
                fullName: `${employee.surname} ${employee.name}`,
              });
            } else {
              this.employeeList.push({
                user: employee,
                fullName: `${employee.surname} ${employee.name} (You)`,
              });
            }
          }
        });
      });
      console.log(this.employeeList);
      console.log(this.savedUsersMeetingRoomList);
    },

    /**
     * Fetches and stores the list of available buildings for booking from the server.
     * Each building includes floors and each floor has its specific details such as available desks and rooms,
     * which are critical for enabling users to make informed booking decisions.
     */

    getBuildings() {
      this.$ApiService.get_buildings().then((res) => {
        this.buildings = res.data;
      });
    },
    /**
     * Counts the number of desks and meeting rooms available on each floor of the selected building.
     * This method is typically called after selecting a building to ensure the latest desk availability and room counts are displayed.
     * It updates each floor's details with the number of available workstations and meeting rooms, using data retrieved from the server.
     */
    countWorkstations() {
      this.selectedBuilding.floors.forEach((floor) => {
        this.$ApiService
          .get_workstations(floor.floorId, floor.buildingId)
          .then((response) => {
            floor.numOfDesks = response.data.workStationResourceList.filter(
              (desk) => desk.type === "desk",
            ).length;
            floor.numOfMeetingRooms =
              response.data.workStationResourceList.filter(
                (desk) => desk.type === "meeting_room",
              ).length;
          });
      });
    },
    /**
     * Returns the icon class for the specified equipment type.
     * This method helps in dynamically displaying the appropriate icons for equipment types listed in desk details.
     * Each equipment type like 'monitor', 'keyboard', or 'mouse' is associated with a specific icon to visually represent it.
     * @param {string} item - The equipment type string which can include one or multiple equipment items.
     * @returns {string} - The icon class for the Vuetify icon component.
     */

    getEquipmentIcon(item) {
      if (item.includes("mouse")) {
        return "mdi-mouse";
      } else if (item.includes("keyboard")) {
        return "mdi-keyboard";
      } else if (item.includes("monitor")) {
        return "mdi-monitor";
      }
      return "";
    },
  },
};
</script>

<style>
.booking-alert {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 9999;
  background-color: white;
  padding: 20px;
}

.text-center {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.text-h4 {
  margin-top: 20px;
}

.v-card__text {
  max-width: 800px;
  overflow-wrap: break-word;
}

ellipse[fill="green"],
ellipse[fill="#40C4FF"] {
  cursor: pointer;
}

.svg-container {
  overflow: auto;
}

#prettySelect .v-list-item__content > .v-list-item-title {
  display: none;
}
</style>
