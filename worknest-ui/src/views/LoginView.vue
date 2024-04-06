<template>
  <Transition enter-active-class="animate__animated animate__zoomIn" appear>
    <header class="text-center">
      <img class="mt-5" style="max-height: 225px" src="/worknest-logo.png"></img>
      <p class="text-h6 font-italic">Your hub for connection and productivity</p>
    </header>
  </Transition>

  <body class="my-5">
    <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
      <div class="text-subtitle-1 text-medium-emphasis">Account</div>

      <v-text-field density="compact" placeholder="Email" prepend-inner-icon="mdi-account-outline"
        variant="outlined" v-model="email"></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password

        <a class="text-caption text-decoration-none text-blue" href="/forgotten-password" rel="noopener noreferrer"
          target="_blank">
          Forgot login password?</a>
      </div>

      <v-text-field :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'" :type="visible ? 'text' : 'password'"
        density="compact" placeholder="Enter your password" prepend-inner-icon="mdi-lock-outline" variant="outlined"
        @click:append-inner="visible = !visible" v-model="password"></v-text-field>

      <v-card class="mb-5" color="surface-variant" variant="tonal">
      </v-card>

      <v-btn class="mb-5" color="blue" size="large" variant="tonal" block @click="login()">
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <a class="text-blue text-decoration-none" href="/sign-up" rel="noopener noreferrer" target="_blank">
          Sign up now <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>
  </body>
</template>
<script>
export default {
  data: () => ({
    visible: false,
    email: "",
    password: ""
  }),
  methods: {
    login(){
      this.$ApiService.login(this.email, this.password).then((res)=> {
        console.log(res)
      });
    }
  }
}
</script>