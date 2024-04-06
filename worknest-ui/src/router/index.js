import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/booking',
      name: 'booking',
      component: () => import('../views/BookingWorkStation.vue')
    },
  ]
})

export default router
