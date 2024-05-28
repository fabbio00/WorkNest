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
      Book your desk
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
                v-model="booking.startDate"
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
                          v-for="equip in item.value.split(', ')"
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
          <v-card elevation="5" class="rounded-lg">
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
              </v-list>
            </v-card-text>
            <v-card-actions>
              <v-btn v-if="!bookingId" color="primary" @click="createBooking"
                >Book</v-btn
              >
              <v-btn v-else color="primary" @click="editBooking">Modify</v-btn>
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
            alertType === 'success' ? 'Confirmed booking' : 'Error booking'
          "
        >
          <v-divider></v-divider>

          <div class="py-12 text-center">
            <v-icon
              class="mb-6"
              size="128"
              :color="alertType === 'success' ? 'success' : 'error'"
            >
              {{
                alertType === "success"
                  ? "mdi-check-circle-outline"
                  : "mdi-alert-outline"
              }}
            </v-icon>

            <div class="text-h4 font-weight-bold" style="word-wrap: break-word">
              {{ alertText }}
            </div>
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
</template>

<script>
/**
 * Vue component for managing desk booking.
 * This component allows users to view available desks, select a desk for booking,
 * and create a new booking.
 *
 * Features:
 * - Displays a date picker to select the booking start date.
 * - Highlights available desks and marks occupied desks.
 * - Allows users to select a desk and view its details before booking.
 * - Submits booking details to create a new booking.
 *
 * Data properties:
 * @vue-data {boolean} isSvgVisible - Flag to determine SVG visibility.
 * @vue-data {Object} booking - Contains details for the booking, including startDate, endDate, status, hasPenalty, workStationId, and userId.
 * @vue-data {Object|null} deskDetails - Details of the selected desk.
 * @vue-data {boolean} alertVisible - Flag to control the visibility of alerts.
 * @vue-data {string} alertText - Text content for the alert.
 * @vue-data {string} alertType - Type of alert.
 * @vue-data {Object} modifyBooking - Contains details for modifying an existing booking, including startDate, endDate, and workStationId.
 * @vue-data {Array} buildings - List of buildings available for booking.
 * @vue-data {Object|null} selectedBuilding - Currently selected building object.
 * @vue-data {Boolean} chooseDeskAlertVisible - Flag to show/hide the alert to prompt user selection.
 * @vue-data {Array} occupiedDesks - List of desks that are already booked and not available.
 * @vue-data {String} userType - User type derived from user session to tailor available options.
 * @vue-data {Array} equipmentItems - List of available equipment types for desks.
 * @vue-data {Number} selectedFloor - Currently selected floor number.
 * @vue-data {Object|null} selectedEquipment - Currently selected equipment options.
 * @vue-data {Boolean|null} wantsWindow - User preference for a desk near a window.
 *
 * Props:
 * @vue-prop {String} bookingId - The ID of the booking to modify.
 *
 * Methods:
 * @vue-method {Function} formatDate - Formats date to YYYY-MM-DD format.
 * @vue-method {Function} formatDate2 - Formats date to ISO 8601 format.
 * @vue-method {Function} findOccupiedDesks - Finds available desks for booking and updates UI accordingly.
 * @vue-method {Function} bookingDesk - Handles desk booking event, retrieves desk details.
 * @vue-method {Function} createBooking - Creates a new booking with the provided details.
 * @vue-method {Function} editBooking - Modifies an existing booking with the provided details.
 * @vue-method {Function} goToRecap - Redirects the user to the booking list page after successful booking or modification.
 * @vue-method getBuildings - Fetches the list of available buildings from the server.
 * @vue-method countWorkstations - Counts desks and meeting rooms available on the selected floor.
 * @vue-method getEquipmentIcon - Returns the appropriate icon class for given equipment type.
 *
 * Usage:
 * This component is used within a Vue application to manage desk booking functionality.
 * It integrates with backend APIs to retrieve desk availability and create new bookings.
 * @subcategory views / bookings
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
      booking: {
        startDate: null,
        endDate: null,
        status: "",
        hasPenalty: false,
        workStationId: "",
        userId: "",
      },
      modifyBooking: {
        startDate: null,
        endDate: null,
        workStationId: "",
      },
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
    this.booking.userId = localStorage.getItem("userId");
    this.$ApiService.find_user_by_id(this.booking.userId).then((res) => {
      this.userType = res.data.type;
    });
    this.getBuildings();
  },
  methods: {
    /**
     * Formats date to YYYY-MM-DD format.
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
     * Formats date to ISO 8601 format.
     * This method takes a string representation of a date and formats it to conform to the
     * ISO 8601 standard. The resulting string includes the date and time in 'YYYY-MM-DDTHH:MM:SSZ' format,
     * suitable for APIs that expect dates in ISO format.
     * @param {string} dateString - The date string to format.
     * @returns {string} - The formatted ISO 8601 date string.
     */
    formatDate2(dateString) {
      const date = new Date(dateString);

      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, "0");
      const day = date.getDate().toString().padStart(2, "0");
      const hours = date.getHours().toString().padStart(2, "0");
      const minutes = date.getMinutes().toString().padStart(2, "0");
      const seconds = date.getSeconds().toString().padStart(2, "0");

      const isoString = `${year}-${month.padStart(2, "0")}-${parseInt(day).toString().padStart(2, "0")}T${hours}:${minutes}:${seconds}+00:00`;

      return isoString;
    },

    /**
     * Finds available desks for booking.
     * This method retrieves the list of available desks for booking based on the selected start date.
     * It queries the backend API to check for desk availability and updates the UI accordingly.
     * Additionally, it highlights desks that are unavailable, such as those already booked or restricted
     * based on user permissions.
     */
    findOccupiedDesks() {
      if (this.booking.startDate !== null && this.selectedBuilding !== null) {
        this.chooseDeskAlertVisible = false;
        const date = this.formatDate(this.booking.startDate);

        this.$ApiService.find_occupied_desks(date).then((occupiedDesks) => {
          this.occupiedDesks = occupiedDesks.data
            .filter((ws) => ws.status != "canceled")
            .map((ws) => ws.workStationId);
          this.isSvgVisible = true;
        });
        this.countWorkstations();
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
      const workStationId = deskId;
      const date = this.formatDate(this.booking.startDate);

      this.$ApiService
        .find_desk_by_id(workStationId)
        .then((res) => {
          this.deskDetails = res.data;
          this.booking.workStationId = workStationId;
        })
        .catch((deskError) => {
          console.error("Error finding desk:", deskError);
        });
    },

    /**
     * Creates a new booking.
     * This method is called when the user confirms the booking after selecting a desk.
     * It sends the booking details to the backend API to create a new booking record.
     * Upon successful booking creation, it displays a success message to the user.
     * If an error occurs during the booking process, it displays an error message instead.
     */
    createBooking() {
      this.booking.startDate = this.formatDate2(this.booking.startDate);
      this.booking.endDate = this.booking.startDate;
      this.booking.status = "active";
      const wsId = this.booking.workStationId;

      this.$ApiService
        .create_booking(this.booking)
        .then((res) => {
          this.alertVisible = true;
          this.alertType = "success";
          this.alertText = "Registration was successful!";

          const deskElement = document.querySelector(`[data-id="${wsId}"]`);
          if (deskElement) {
            deskElement.setAttribute("fill", "red");
            deskElement.style.pointerEvents = "none";
          }

          const userId = this.booking.userId;
          const bookingDate = new Date(this.booking.startDate);
          const formattedDate = bookingDate.toLocaleDateString("en-US", {
            year: "numeric",
            month: "long",
            day: "numeric",
          });

          this.$ApiService.find_desk_by_id(wsId).then((res) => {
            this.$ApiService
              .find_user_by_id(userId)
              .then((u) => {
                const emailData = {
                  to: u.data.email,
                  subject: "Desk Booking Confirmation",
                  text: `Your booking for the ${res.data.name} has been successfully confirmed for ${formattedDate}.`,
                };

                this.$ApiService
                  .send_mail(emailData)
                  .then((emailRes) => {})
                  .catch((emailError) => {
                    console.error("Error sending email:", emailError);
                  });
              })
              .catch((userError) => {
                console.error("Error finding user:", userError);
              });
          });
        })
        .catch((error) => {
          this.alertVisible = true;
          this.alertType = "error";
          this.alertText = "Something went wrong, please try again!";
        });
    },

    /**
     * Modifies an existing booking with the provided details.
     * This method is called when the user confirms the modification of a booking.
     * It sends the modified booking details to the backend API to update the booking record.
     * Upon successful modification, it displays a success message to the user.
     * If an error occurs during the modification process, it displays an error message instead.
     */
    editBooking() {
      this.modifyBooking.startDate = this.formatDate2(this.booking.startDate);
      this.modifyBooking.endDate = this.modifyBooking.startDate;
      this.modifyBooking.workStationId = this.booking.workStationId;
      const wsId = this.modifyBooking.workStationId;

      this.$ApiService
        .modify_booking(this.bookingId, this.modifyBooking)
        .then((res) => {
          this.alertVisible = true;
          this.alertType = "success";
          this.alertText = "Modify was successful!";

          const deskElement = document.querySelector(`[data-id="${wsId}"]`);
          if (deskElement) {
            deskElement.setAttribute("fill", "red");
            deskElement.style.pointerEvents = "none";
          }

          const userId = this.booking.userId;
          const bookingDate = new Date(this.booking.startDate);
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
                subject: "Edit Booking Confirmed",
                text: `Your booking for the ${res.data.workStation.name} has been successfully updated for ${formattedDate}.`,
              };

              this.$ApiService
                .send_mail(emailData)
                .then((emailRes) => {
                  console.log(emailRes.data);
                })
                .catch((emailError) => {
                  console.error("Error sending email:", emailError);
                });
            })
            .catch((userError) => {
              console.error("Error finding user:", userError);
            });
        })
        .catch((error) => {
          this.alertVisible = true;
          this.alertType = "error";
          this.alertText = "Something went wrong, please try again!";
        });
    },
    /**
     * Redirects the user to the bookings recap page after a successful booking or booking modification.
     * This method checks the type of alert ('success' or 'error') and redirects based on the result of the booking process.
     * Upon successful booking actions, the user is navigated to a page where all their bookings are listed.
     */
    goToRecap() {
      this.alertVisible = false;
      if (this.alertType == "success") {
        this.$router.push("/bookingList");
      }
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
