import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/sign-up',
      name: 'sign_up',
      component: () => import('../views/SignUpView.vue')
    },    
  ]
})

export default router