<template>
  <Transition enter-active-class="animate__animated animate__zoomIn" appear>
    <header class="text-center">
      <img class="mt-5" style="max-height: 225px" src="/worknest-logo.png"></img>
      <p class="text-h6 font-italic">Your hub for connection and productivity</p>
    </header>
  </Transition>

  <body class="my-5">
    <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg" @keyup.enter="login">
      <Transition enter-active-class="animate__animated animate__flipInX">
        <v-alert v-if="invalidCredentials" border="top" type="warning" class="mb-2">
          Invalid email or password</v-alert>
      </Transition>
      <div class="text-subtitle-1 text-medium-emphasis text-left">Account</div>

      <v-text-field density="compact" placeholder="Email" prepend-inner-icon="mdi-email-outline" variant="outlined"
        v-model="email" tabindex="1" color="indigo"></v-text-field>

      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password
      </div>

      <v-text-field :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'" :type="visible ? 'text' : 'password'"
        density="compact" placeholder="Enter your password" prepend-inner-icon="mdi-lock-outline" variant="outlined"
        @click:append-inner="visible = !visible" v-model="password" tabindex="2" color="indigo"></v-text-field>

      <v-card class="mb-5" color="surface-variant" variant="tonal">
      </v-card>

      <v-btn class="mb-5" color="blue" size="large" variant="tonal" block @click="login"
        :append-icon="'mdi-send-variant'">
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <a class="text-blue text-decoration-none" href="/sign-up" rel="noopener noreferrer">
          Sign up now <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>
  </body>
</template>

<script>

import UserServices from "../services/user.services";

/**
  * User Login View Component
  * 
  * This component handles the user login process, including capturing user credentials,
  * validating input, encrypting the password, and submitting the login data.
  * 
  *
  * Features:
  * <ol>
  *   <li>Email and password fields for user authentication.</li>  
  *   <li>Visibility toggle for the password field.</li>  
  *   <li>Alerts user on invalid credentials.</li>  
  *   <li>Redirects to the main page upon successful login.</li>
  * </ol>
  *
  * Data properties:
  * @vue-data {Boolean} visible - Controls the visibility of the password field. Default: false.
  * @vue-data {String} email - The user's email input. Default: "" (empty string).
  * @vue-data {String} password - The user's password input. Default: "" (empty string).
  * @vue-data {Boolean} invalidCredentials - Indicates if the credentials provided are invalid. Default: false.
  * 
  * Usage:
  * The component is used as a login form in the user authentication process. Users enter their email and password, which are validated and, upon successful validation, are encrypted and submitted for login.
  * @subcategory views
  */

export default {
  data() {
    return {
      visible: false,
      email: "",
      password: "",
      invalidCredentials: false
    }
  },
  methods: {
    /**
     * login
     * Called when the user attempts to log in via the login form. It encrypts the provided password and sends the
     * email and encrypted password to the API for validation. If the response indicates failure (e.g., 'unauthorized'),
     * it sets the invalidCredentials flag to true, which triggers the display of an error alert.
     * On successful authentication, the method stores the user's session information and redirects to the homepage.
     */
    login() {
      this.$ApiService.login(this.email, UserServices.encryptPassword(this.password)).then((res) => {
        if (res == "unauthorized") {
          this.invalidCredentials = true;
        }
        else if (res.data && res.data.id) {
          const expirationTime = Date.now() + (3 * 3600 * 1000);
          localStorage.setItem('expirationTime', expirationTime);
          localStorage.setItem('userId', res.data.id);
          this.invalidCredentials = false;
          window.location.href="/";
        }
      });
    }
  }
}
</script>