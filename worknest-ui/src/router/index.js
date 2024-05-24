import { createRouter, createWebHistory } from "vue-router";
import apiServices from "@/services/api.services";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/sign-up",
      name: "sign_up",
      component: () => import("../views/SignUpView.vue"),
    },
    {
      path: "/",
      name: "home",
      meta: {
        requiresAuth: true,
      },
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
    },
    {
      path: "/booking/:bookingId",
      name: "modify_booking",
      props: (route) => ({ bookingId: route.params.bookingId }),
      component: () => import("../views/bookings/BookingWorkStationView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/booking",
      name: "booking",
      component: () => import("../views/bookings/BookingWorkStationView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/bookingList",
      name: "bookingList",
      component: () => import("../views/bookings/BookingListView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/company-registration",
      name: "company_registration",
      component: () => import("../views/admin/CompanyRegistrationView.vue"),
      meta: {
        requiresAuth: true,
        role: "ADMINISTRATOR",
      },
    },
    {
      path: "/businessEmployeesList",
      name: "businessEmployeesList",
      component: () => import("../views/business/employees/BusinessEmployeesListView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/businessBookingDesks",
      name: "businessBookingDesks",
      component: () => import("../views/business/bookings/BusinessBookingWorkStations.vue"),
      meta: {
        requiresAuth: true,
      },
    },

  ],
});

router.beforeEach(async (to, from, next) => {
  const user = localStorage.getItem("userId");
  const expirationTime = localStorage.getItem("expirationTime");

  if (!to.meta.requiresAuth) {
    // Non-protected route, allow access
    return next();
  }

  // Check if user is authenticated
  if (!user || !expirationTime || Date.now() >= parseInt(expirationTime)) {
    localStorage.removeItem("userId");
    // User is not authenticated, redirect to login
    return next("/login");
  }

  // Check if the route requires a specific role
  if (to.meta.role) {
    try {
      const res = await apiServices.find_user_by_id(user);
      if (to.meta.role === "ADMINISTRATOR" && res.data.type !== "ADMINISTRATOR") {
        // User does not have the required role, redirect to login
        alert("You are not an admin!");
        return next("/login");
      } else if (to.meta.role === "BUSINESS" && res.data.type !== "BUSINESS") {
        // User does not have the required role, redirect to login
        alert("You are not a business user!");
        return next("/login");
      }
    } catch (error) {
      // Handle error or log error
      alert("Error fetching user details or insufficient permissions!");
      console.error("Error fetching user details:", error);
      return next("/login");
    }
  }

  // If all checks pass, proceed to the route
  return next();
});

export default router;
