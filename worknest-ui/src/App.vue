<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <header>
    <v-card class="d-flex" style="z-index: 9999">
      <v-layout>
        <v-navigation-drawer
          v-if="showSidebar"
          expand-on-hover
          rail
          clipped
          class="bg-blue-darken-3"
          permanent
        >
          <v-list>
            <v-list-item
              prepend-avatar="/worknest-logo.ico"
              :title="user.email"
              :subtitle="user.name + ' ' + user.surname"
              @click="redirect('/')"
            ></v-list-item>
          </v-list>
          <v-divider thickness="2"></v-divider>
          <v-list density="compact" nav>
            <v-list-item
              v-if="user.type !== 'ADMINISTRATOR'"
              prepend-icon="mdi-calendar-plus-outline"
              title="Make a booking"
              @click="redirect('/booking')"
            >
              <template v-slot:title>
                <p class="font-weight-black">Make a booking</p>
              </template>
            </v-list-item>
            <v-list-item
              v-if="user.type !== 'ADMINISTRATOR'"
              prepend-icon="mdi-calendar-month-outline"
              title="View your bookings"
              @click="redirect('/bookingList')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View your bookings</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-office-building-plus"
              title="Add new company"
              @click="redirect('/company-registration')"
            >
              <template v-slot:title>
                <p class="font-weight-black">Add new company</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-calendar-multiple"
              title="View all bookings"
              @click="redirect('/')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all bookings</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-domain"
              title="View all companies"
              @click="redirect('/')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all companies</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-account-multiple"
              title="View all users"
              @click="redirect('/')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all users</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'BUSINESS'"
              prepend-icon="mdi-calendar-multiple"
              title="View all bookings"
              @click="redirect('/businessBookingsList')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all bookings</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'BUSINESS'"
              prepend-icon="mdi-account-multiple"
              title="View all employees"
              @click="redirect('/businessEmployeesList')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all employees</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'BUSINESS'"
              prepend-icon="mdi-table"
              title="Make a booking for employees"
              @click="redirect('/businessBookingDesks')"
            >
              <template v-slot:title>
                <p class="font-weight-black">Make a booking for employees</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'BUSINESS'"
              prepend-icon="mdi-table-cog"
              title="Manage business bookings"
              @click="redirect('/businessBookingsListDelete')"
            >
              <template v-slot:title>
                <p class="font-weight-black">Manage business bookings</p>
              </template>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>
      </v-layout>
    </v-card>
  </header>
  <main>
    <RouterView />
  </main>
</template>

<script>
export default {
  data() {
    return {
      showSideBar: false,
      current: "",
      user: null,
      isExpanded: false,
    };
  },

  async beforeMount() {
    this.showSideBar = this.$route.meta.requiresAuth === true ? true : false;
    const userId = localStorage.getItem("userId");
    if (userId) {
      await this.$ApiService.find_user_by_id(userId).then((res) => {
        this.user = res.data;
      });
    }
  },

  beforeUpdate() {
    const expirationTime = localStorage.getItem("expriationTime");
    if (!expirationTime || Date.now() > parseInt(expirationTime)) {
      this.$router.replace("/login");
      localStorage.removeItem("userId");
    }
  },

  computed: {
    showSidebar() {
      return this.$route.meta.requiresAuth;
    },
  },

  methods: {
    redirect(url) {
      this.current = url;
      this.$router.push(url);
    },
  },
};
</script>

<style>

.v-list-item .font-weight-black {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.v-list-item:hover .font-weight-black {
  overflow: visible;
  white-space: normal;
  text-overflow: clip;
}

</style>
