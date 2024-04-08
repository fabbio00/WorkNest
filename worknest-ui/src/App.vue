<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
  </header>
  <RouterView />
</template>
<script>
export default {
  data() {
    return {
    }
  },
  beforeUpdate(){
    if(localStorage.getItem("expriationTime") == null || Date.now() > parseInt(localStorage.getItem("expriationTime"))){
      this.$router.replace("/login");
      localStorage.removeItem("userId");
    }
  }
}
</script>