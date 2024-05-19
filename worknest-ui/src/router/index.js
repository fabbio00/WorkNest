import { createRouter, createWebHistory } from "vue-router";

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
      path: "/company/employeesList",
      name: "employeesList",
      component: () => import("../views/company/EmployeesListView.vue"),
    },

  ],
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const user = localStorage.getItem("userId");
    const expirationTime = localStorage.getItem("expirationTime");
    if (user && expirationTime && Date.now() < parseInt(expirationTime)) {
      // User is authenticated, proceed to the route
      next();
    } else {
      localStorage.removeItem("userId");
      // User is not authenticated, redirect to login
      next("/login");
    }
  } else {
    // Non-protected route, allow access
    next();
  }
});

export default router;
