<script setup>
import { useVuelidate } from "@vuelidate/core";
import { required, email } from "@vuelidate/validators";
</script>

<template>
  <v-form @submit.prevent="createUser()">
    <v-card
      class="mx-auto rounded-lg pa-12 mt-12"
      style="background-color: #3f51b50e"
      max-width="90%"
      elevation="10"
    >
      <p class="text-center text-h2 mb-3 font-weight-bold">Sign-Up</p>
      <div class="mb-5">
        <h2 class="text-h6 font-weight-thin d-inline mr-2">
          Do you have an account?
        </h2>

        <p class="d-inline">
          <a href="/login" class="">
            <v-icon size="s" icon="mdi-logout"></v-icon>
            <span class="text-body-2">Go to login page</span>
          </a>
        </p>
      </div>
      <v-row>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Name</div>
          <v-text-field
            v-model="user.name"
            label="First name"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Surname</div>
          <v-text-field
            v-model="user.surname"
            label="Last name"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Username</div>
          <v-text-field
            v-model="user.username"
            label="Username"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">E-mail</div>
          <v-text-field
            v-model="user.email"
            label="E-mail"
            variant="outlined"
            color="indigo"
            :error-messages="v$.user.email.$errors.map((e) => e.$message)"
            @blur="v$.user.email.$touch"
            @input="v$.user.email.$touch"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">TAX Code</div>
          <v-text-field
            v-model="user.taxCode"
            label="TAX Code"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Company code</div>
          <v-text-field
            v-model="user.companyCode"
            label="Company code"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Password</div>
          <v-text-field
            v-model="user.password"
            label="Password"
            variant="outlined"
            color="indigo"
            :type="passwordVisible ? 'text' : 'password'"
            :append-inner-icon="passwordVisible ? 'mdi-eye-off' : 'mdi-eye'"
            @click:append-inner="passwordVisible = !passwordVisible"
            :error-messages="
              v$.user.password.$invalid
                ? 'Password must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 8 characters long'
                : []
            "
            @blur="v$.user.password.$touch"
            @input="v$.user.password.$touch"
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Confirm password</div>
          <v-text-field
            v-model="password_confirmed"
            label="Confirm Password"
            :type="passwordConfirmedVisible ? 'text' : 'password'"
            :append-inner-icon="
              passwordConfirmedVisible ? 'mdi-eye-off' : 'mdi-eye'
            "
            @click:append-inner="
              passwordConfirmedVisible = !passwordConfirmedVisible
            "
            :error-messages="
              !isPasswordValid && v$.password_confirmed.$dirty
                ? 'Password are different!'
                : []
            "
            @blur="v$.password_confirmed.$touch"
            @input="validatePassword"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="4" class="d-flex justify-left align-self-center">
          <v-switch
            label="Barrier free"
            v-model="user.barrerFreeFlag"
            color="blue-darken-3"
          >
            <template v-slot:label>
              <div>
                <v-icon
                  icon="mdi-wheelchair-accessibility mx-2"
                  size="x-large"
                ></v-icon>
                <p class="text-body-1 font-weight-bold d-inline">
                  Barrier free
                </p>
              </div>
            </template>
          </v-switch>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" class="d-flex justify-center align-self-center">
          <v-btn
            :disabled="!isPasswordValid || v$.user.$invalid"
            prepend-icon="mdi-check"
            size="x-large"
            color="blue-darken-4"
            type="submit"
          >
            <template v-slot:prepend>
              <v-icon size="large" color="green-lighten-1"></v-icon>
            </template>
            Submit</v-btn
          >
        </v-col>
      </v-row>
    </v-card>
  </v-form>
</template>

<script>
import UserServices from "../services/user.services";
import { helpers } from "@vuelidate/validators";
import axios from "axios";

const emailUsed = (value) =>
  axios
    .post("http://localhost:8080/users/email", { email: value })
    .then((res) => {
      return !res.data.email;
    });

const checkPassword = helpers.regex(
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&,-.#'^"£=(){}%€;:~<>+|/])[A-Za-z\d@$!%*?&,-.#'^"£=(){}%€;:~<>+|/]{8,30}$/,
);

/**
 * User Registration Form Component
 *
 * This component handles the user registration process, including collecting user details,
 * validating input, encrypting the password, and submitting the form data.
 *
 * Features:
 * <ol>
 *   <li>Fields for user information like name, surname, email, username, password, tax code, and company code.</li>
 *   <li>Password and confirm password fields with visibility toggle.</li>
 *   <li>Form validation including email format and password requirements.</li>
 *   <li>Password encryption before submission.</li>
 *   <li>Redirects to the login page upon successful registration.</li>
 * </ol>
 *
 *
 * Data properties:
 * @vue-data {Object} user - Contains user input fields including name, surname, email, username, password, taxCode, companyCode, and barrerFreeFlag.
 * @vue-data {Boolean} isPasswordValid - Indicates whether the password matches the confirmation password.
 * @vue-data {String} password_confirmed - Stores the user's input for password confirmation.
 * @vue-data {Boolean} passwordVisible - Controls the visibility of the password field.
 * @vue-data {Boolean} passwordConfirmedVisible - Controls the visibility of the confirm password field.
 *
 * Validations:
 * Uses Vuelidate to validate the user's input. Fields are checked for required values, the email is validated for format, and the password is checked against a defined pattern.
 *
 * Events:
 * @vue-event {String} user-registration-submit - Emitted when the user submits the registration form and it is validated successfully.
 *
 * Usage:
 * The component is used as a sign-up form in the user registration process. Users fill in their details, including a valid email address and a password that meets the specified criteria. The form validates the input and upon successful validation, encrypts the password and registers the user.
 * @subcategory views
 */

export default {
  data() {
    return {
      user: {
        name: "",
        surname: "",
        email: "",
        username: "",
        password: "",
        taxCode: "",
        companyCode: "",
        type: "Employee",
        barrerFreeFlag: false,
      },
      isPasswordValid: false,
      password_confirmed: "",
      passwordVisible: false,
      passwordConfirmedVisible: false,
      v$: useVuelidate({ $lazy: true, $autoDirty: true }),
    };
  },
  methods: {
    /**
     * Validates the password by checking if it matches the confirmed password.
     * Updates the `isPasswordValid` data property accordingly.
     */
    validatePassword() {
      this.isPasswordValid =
        this.user.password && this.user.password === this.password_confirmed;
    },

    /**
     * Creates a new user with the form data after encrypting the password.
     * Upon successful creation, the user is redirected to the login page.
     * If the password validation fails, it alerts the user.
     */
    createUser() {
      if (this.isPasswordValid) {
        let encryptedPassword = UserServices.encryptPassword(
          this.user.password,
        );
        let userWithEncryptedPassword = {
          ...this.user,
          password: encryptedPassword,
        };
        this.$ApiService.create_user(userWithEncryptedPassword).then((res) => {
          this.$router.push("/login");
        });
      } else {
        alert("Passwords do not match!");
      }
    },
  },
  validations: {
    user: {
      email: {
        required,
        email,
        isUnique: helpers.withMessage(
          "This email is already in use",
          helpers.withAsync(emailUsed),
        ),
      },
      password: { required, checkPassword },
    },
    password_confirmed: { required },
  },
};
</script>
