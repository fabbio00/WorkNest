<template>
  <rect
    stroke="#000"
    rx="8"
    height="56"
    width="28"
    :y="desk.cy - 27.57144"
    :x="desk.leftPosition ? desk.cx - 11.16667 : desk.cx - 16.83332"
    fill="#f2efef"
  />
  <rect
    stroke="#000"
    rx="2"
    height="25"
    width="13"
    :y="desk.cy - 12.72619"
    :x="desk.leftPosition ? desk.cx - 24.16667 : desk.cx + 11.41652"
    fill="#f2efef"
  />

  <ellipse
    stroke="#000"
    ry="16"
    rx="16"
    :cy="desk.cy"
    :cx="desk.cx"
    fill="#ffffff"
  />
  <ellipse
    :data-id="desk.id"
    stroke="#000"
    ry="12"
    rx="12"
    :cy="desk.cy"
    :cx="desk.cx"
    :fill="isOccupied ? 'red' : hasRequests ? '#40C4FF' : 'green'"
    @click="handleBookingDesk($event)"
  />
</template>
<script>
/**
 * Desk
 * This component visualizes an individual desk on a floor layout. It allows users to interact with the desk,
 * providing visual feedback based on the desk's current state (occupied, has requests, or available).
 *
 * Features:
 * - Renders a desk as an SVG rectangle with additional elements to indicate its position and state.
 * - Provides interactivity, allowing users to click on available desks to potentially book them.
 * - Visually differentiates desks based on their occupancy status and any special requests.
 *
 * Props:
 * @vue-prop {Object} desk - An object containing the desk's details, including its position (cx, cy) and ID.
 * @vue-prop {Boolean} isOccupied - Indicates whether the desk is currently occupied.
 * @vue-prop {Boolean} hasRequests - Optional. Indicates whether there are special requests associated with the desk (like specific equipment needs).
 *
 * Methods:
 * @vue-method handleBookingDesk - Emits an event when an available desk is clicked, facilitating further action like opening a booking modal.
 *
 * Usage:
 * The Desk component is used within the Floor component to render each desk based on the floor data provided.
 * It should be provided with the necessary desk data and state flags (occupied, requests) to function properly.
 *
 * Example:
 * <Desk :desk="deskData" :isOccupied="occupiedStatus" :hasRequests="requestStatus" />
 *
 * @subcategory components
 */

export default {
  props: {
    desk: {
      type: Object,
      required: true,
    },
    isOccupied: {
      type: Boolean,
      required: true,
    },
    hasRequests: {
      type: Boolean,
    },
  },
  data() {
    return {};
  },
  methods: {
    /**
     * Emits a 'deskClicked' event with the desk ID when the desk is clicked.
     * This method is triggered when a user clicks on an available desk (not occupied).
     * It facilitates the booking process by notifying parent components of the selected desk.
     *
     * @param {Event} event - The click event on the desk element.
     */
    handleBookingDesk(event) {
      if (!this.isOccupied) {
        const workStationId = event.target.getAttribute("data-id");
        this.$emit("deskClicked", workStationId);
      }
    },
  },
};
</script>
