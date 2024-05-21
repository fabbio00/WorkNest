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
              title=""
              @click="redirect('/booking')"
            >
              <template v-slot:title>
                <p class="font-weight-black">Make a booking</p>
              </template>
            </v-list-item>
            <v-list-item
              v-if="user.type !== 'ADMINISTRATOR'"
              prepend-icon="mdi-calendar-month-outline"
              title=""
              @click="redirect('/bookingList')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View your bookings</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-office-building-plus"
              title=""
              @click="redirect('/company-registration')"
            >
              <template v-slot:title>
                <p class="font-weight-black">Add new company</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-calendar-multiple"
              title=""
              @click="redirect('/')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all bookings</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-domain"
              title=""
              @click="redirect('/')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all company</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'ADMINISTRATOR'"
              prepend-icon="mdi-account-multiple"
              title=""
              @click="redirect('/')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all users</p>
              </template>
            </v-list-item>

            <v-list-item
              v-if="user.type === 'BUSINESS'"
              prepend-icon="mdi-calendar-multiple"
              title=""
              @click="redirect('/businessBookingsList')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all bookings</p>
              </template>
            </v-list-item>


            <v-list-item
              v-if="user.type === 'BUSINESS'"
              prepend-icon="mdi-account-multiple"
              title=""
              @click="redirect('/businessEmployeesList')"
            >
              <template v-slot:title>
                <p class="font-weight-black">View all employees</p>
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
/**
 * The root component for a Vue application.
 * It sets up the main layout of the application, including a header with a sidebar navigation drawer
 * and a main content area where router views are displayed.
 *
 * Features:
 * <ol>
 * <li>A persistent navigation drawer for navigating different parts of the application.</li>
 * <li>Automatic redirection to the login page if the user is not authenticated.</li>
 * <li>Dynamic display of the sidebar based on the route's metadata requiring authentication.</li>
 * </ol>
 *
 * @vue-data {boolean} showSidebar - Controls the visibility of the sidebar based on user authentication.
 * @vue-data {string} current - Represents the current active route.
 * @vue-data {Object} user - Stores the current user's details.
 *
 * Computed properties:
 * @vue-computed {boolean} showSidebar - Determines if the sidebar should be displayed based on the current route's metadata.
 *
 * Lifecycle hooks:
 * @vue-mount {Function} beforeMount - Checks user authentication status and fetches user details before the component mounts.
 * @vue-update {Function} beforeUpdate - Checks the user's session expiration and redirects to the login page if necessary.
 *
 * Methods:
 * @vue-method {Function} redirect - Redirects the user to a specified route.
 *
 * Usage:
 * <App/>
 * This component should be used as the root component in a Vue application that requires user authentication and navigation.
 * It depends on Vue Router for routing and Vuetify for UI components.
 */

export default {
  data() {
    return {
      // showSidebar: Determines whether the navigation drawer should be visible.
      // It is false by default and set based on route meta in beforeMount hook.
      showSideBar: false,
      // current: Tracks the current route that the user navigated to.
      current: "",
      // user: Stores information about the currently authenticated user.
      user: null,
    };
  },

  async beforeMount() {
    // Here we check if the route requires authentication and set the sidebar visibility.
    // We also make an API call to fetch user details.
    this.showSideBar = this.$route.meta.requiresAuth === true ? true : false;
    const userId = localStorage.getItem("userId");
    if (userId) {
      await this.$ApiService.find_user_by_id(userId).then((res) => {
        this.user = res.data;
      });
    }
  },

  beforeUpdate() {
    // This hook checks if the user's session has expired and, if so,
    // redirects them to the login page and clears the local storage.
    const expirationTime = localStorage.getItem("expriationTime");
    if (!expirationTime || Date.now() > parseInt(expirationTime)) {
      this.$router.replace("/login");
      localStorage.removeItem("userId");
    }
  },

  computed: {
    /**
     * Computed property that determines if the sidebar should be shown
     * based on the authentication requirement from the current route's meta.
     */
    showSidebar() {
      return this.$route.meta.requiresAuth;
    },
  },

  methods: {
    /**
     * Method to redirect the user to a different route programmatically.
     * It updates the current route and uses Vue Router to change the view.
     * @param {string} url - The route path to redirect to.
     */
    redirect(url) {
      this.current = url;
      this.$router.push(url);
    },
  },
};
</script>
