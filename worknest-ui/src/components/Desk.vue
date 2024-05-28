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
    handleBookingDesk(event) {
      if (!this.isOccupied) {
        const workStationId = event.target.getAttribute("data-id");
        this.$emit("deskClicked", workStationId);
      }
    },
  },
};
</script>
