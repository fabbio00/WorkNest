<script setup>
import { useVuelidate } from "@vuelidate/core";
import { required, email, numeric, minLength } from "@vuelidate/validators";
</script>

<template>
  <p class="text-center text-h3 font-weight-bold mt-4 font-italic">
    Company Registration
  </p>
  <v-form @submit.prevent="createCompany()">
    <v-card
      class="mx-auto rounded-lg pa-12 mt-12"
      style="background-color: #3f51b50e"
      max-width="90%"
      elevation="10"
    >
      <p class="text-left text-h4 font-weight-bold">Company Information</p>
      <v-divider class="my-4"></v-divider>
      <v-row class="mx-1">
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Business Name</div>
          <v-text-field
            v-model="company.name"
            label="Business name"
            variant="outlined"
            color="indigo"
            :error-messages="v$.company.name.$errors.map((e) => e.$message)"
            @blur="handleBusinessNameBlur"
            @input="v$.company.name.$touch"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">E-mail</div>
          <v-text-field
            v-model="company.email"
            label="E-mail"
            variant="outlined"
            color="indigo"
            :error-messages="v$.company.email.$errors.map((e) => e.$message)"
            @blur="v$.company.email.$touch"
            @input="v$.company.email.$touch"
            class="prova"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mx-1">
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">VAT Code</div>
          <v-text-field
            v-model="company.vatCode"
            label="VAT Code"
            variant="outlined"
            color="indigo"
            :error-messages="v$.company.vatCode.$errors.map((e) => e.$message)"
            @blur="v$.company.vatCode.$touch"
            @input="v$.company.vatCode.$touch"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Phone</div>
          <v-text-field
            v-model="company.phone"
            label="Phone"
            variant="outlined"
            color="indigo"
            :error-messages="v$.company.phone.$errors.map((e) => e.$message)"
            @blur="v$.company.phone.$touch"
            @input="v$.company.phone.$touch"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mx-1">
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Company Code</div>
          <v-text-field
            v-model="company.companyCode"
            variant="outlined"
            color="indigo"
            :value="company.companyCode"
            readonly
          ></v-text-field>
        </v-col>
      </v-row>

      <p class="text-left text-h4 font-weight-bold my-2">
        Business User Information
      </p>
      <v-divider class="my-4"></v-divider>
      <v-row class="mx-1">
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Name</div>
          <v-text-field
            v-model="business_user.name"
            label="Name"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Surname</div>
          <v-text-field
            v-model="business_user.surname"
            label="Surname"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mx-1">
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">E-mail</div>
          <v-text-field
            v-model="business_user.email"
            label="E-mail"
            variant="outlined"
            color="indigo"
            :error-messages="
              v$.business_user.email.$errors.map((e) => e.$message)
            "
            @blur="v$.business_user.email.$touch"
            @input="v$.business_user.email.$touch"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Username</div>
          <v-text-field
            v-model="business_user.username"
            label="Username"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="mx-1">
        <v-col cols="12" md="6">
          <div class="text-overline text-large-emphasis">Password</div>
          <v-text-field
            v-model="business_user.password"
            label="Password"
            variant="outlined"
            color="indigo"
            :type="passwordVisible ? 'text' : 'password'"
            :append-inner-icon="passwordVisible ? 'mdi-eye-off' : 'mdi-eye'"
            @click:append-inner="passwordVisible = !passwordVisible"
            :error-messages="
              v$.business_user.password.$invalid
                ? 'Password must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 8 characters long'
                : []
            "
            @blur="v$.business_user.password.$touch"
            @input="v$.business_user.password.$touch"
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
                ? 'Passwords are different!'
                : []
            "
            @blur="v$.password_confirmed.$touch"
            @input="validatePassword"
            variant="outlined"
            color="indigo"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" class="d-flex justify-center align-self-center">
          <v-btn
            :disabled="
              !isPasswordValid ||
              v$.business_user.$invalid ||
              v$.company.$invalid
            "
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
  <v-fade-transition hide-on-leave>
    <v-dialog v-model="alertVisible" persistent min-width="500" max-width="800">
      <v-card
        class="mx-auto registration-alert"
        elevation="16"
        min-width="500"
        max-width="800"
        :title="
          alertType === 'success'
            ? 'Registration successful'
            : 'Error during registration'
        "
      >
        <v-divider></v-divider>

        <div class="py-12 text-center">
          <v-icon
            class="mb-6"
            size="128"
            :color="alertType === 'success' ? 'success' : 'error'"
          >
            {{
              alertType === "success"
                ? "mdi-check-circle-outline"
                : "mdi-alert-outline"
            }}
          </v-icon>

          <div class="text-h4 font-weight-bold" style="word-wrap: break-word">
            {{ alertText }}
          </div>
        </div>

        <v-divider></v-divider>

        <div class="pa-4 text-end">
          <v-btn
            class="text-none"
            color="medium-emphasis"
            min-width="92"
            variant="outlined"
            rounded
            @click="clear"
          >
            Close
          </v-btn>
        </div>
      </v-card>
    </v-dialog>
  </v-fade-transition>
</template>

<script>
import UserServices from "../../services/user.services";
import { helpers } from "@vuelidate/validators";
import axios from "axios";

const emailUsed = (value) =>
  axios
    .post("http://localhost:8080/users/email", { email: value })
    .then((res) => {
      return !res.data.email;
    });

const isCompanyCodeUnique = async (code) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/companies/companyCode",
      { companyCode: code },
    );
    return !response.data.companyCode; // Return true if the company code is unique
  } catch (error) {
    if (error.response && error.response.status === 404) {
      return true; // Company code does not exist
    }
    return false;
  }
};

const checkPassword = helpers.regex(
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&,-.#'^"£=(){}%€;:~<>+|/])[A-Za-z\d@$!%*?&,-.#'^"£=(){}%€;:~<>+|/]{8,30}$/,
);

/**
 * Company Registration Form Component
 *
 * This component handles the company registration process maded by the admin, including collecting company details,
 * validating input, generating a unique company code, and submitting the form data.
 *
 * Features:
 * <ol>
 *   <li>Fields for company information like business name, email, VAT code, phone, and company code.</li>
 *   <li>Fields for business user information like name, surname, email, username, and password.</li>
 *   <li>Password and confirm password fields with visibility toggle.</li>
 *   <li>Form validation including email format and password requirements.</li>
 *   <li>Company code generation based on the business name.</li>
 *   <li>Password encryption before submission.</li>
 *   <li>Displays success or error message upon registration.</li>
 * </ol>
 *
 * Data properties:
 * @vue-data {Object} company - Contains company input fields including name, email, vatCode, phone, and companyCode.
 * @vue-data {Object} business_user - Contains business user input fields including name, surname, email, username, password, taxCode, and companyCode.
 * @vue-data {Boolean} isPasswordValid - Indicates whether the password matches the confirmation password.
 * @vue-data {String} password_confirmed - Stores the user's input for password confirmation.
 * @vue-data {Boolean} passwordVisible - Controls the visibility of the password field.
 * @vue-data {Boolean} passwordConfirmedVisible - Controls the visibility of the confirm password field.
 * @vue-data {Boolean} alertVisible - Controls the visibility of the registration alert dialog.
 * @vue-data {String} alertText - Stores the text for the registration alert dialog.
 * @vue-data {String} alertType - Stores the type of alert ("success" or "error").
 * @vue-data {Array} companyCodeError - Stores error messages related to company code generation.
 *
 * Validations:
 * Uses Vuelidate to validate the company's and business user's input. Fields are checked for required values, email is validated for format, phone is checked for numeric value, and password is checked against a defined pattern.
 *
 * Events:
 * @vue-event {String} company-registration-submit - Emitted when the company submits the registration form and it is validated successfully.
 *
 * Usage:
 * The component is used as a sign-up form for company registration. Companies fill in their details, including a valid email address and a password that meets the specified criteria. The form validates the input and upon successful validation, generates a unique company code, encrypts the password, and registers the company and its business user.
 * @subcategory views/admin
 */

export default {
  data() {
    return {
      company: {
        name: "",
        email: "",
        vatCode: "",
        phone: "",
        companyCode: "",
      },
      business_user: {
        name: "",
        surname: "",
        email: "",
        username: "",
        password: "",
        taxCode: "",
        companyCode: "",
        type: "BUSINESS",
        barrerFreeFlag: false,
      },
      companyInitialState: { ...this.company },
      business_userInitialState: { ...this.business_user },
      isPasswordValid: false,
      password_confirmed: "",
      passwordVisible: false,
      passwordConfirmedVisible: false,
      v$: useVuelidate({ $lazy: true, $autoDirty: true }),
      alertVisible: false,
      alertText: "",
      alertType: "success",
      companyCodeError: [],
    };
  },
  methods: {
    /**
     * Validates the password by checking if it matches the confirmed password.
     * Updates the `isPasswordValid` data property accordingly.
     */
    validatePassword() {
      this.isPasswordValid =
        this.business_user &&
        this.business_user.password === this.password_confirmed;
    },

    /**
     * Handles the blur event on the business name field.
     * Generates a unique company code based on the business name.
     */
    async handleBusinessNameBlur() {
      this.v$.company.name.$touch();
      if (this.company.name.length >= 3) {
        let isUnique = false;
        while (!isUnique) {
          const code =
            this.company.name.slice(0, 3) + Math.floor(Math.random() * 1000);
          isUnique = await isCompanyCodeUnique(code);
          if (isUnique) {
            this.company.companyCode = code;
            this.companyCodeError = [];
          } else {
            this.companyCodeError = [
              "Company code already in use. Generating a new one...",
            ];
          }
        }
      } else {
        this.company.companyCode = "";
      }
    },

    /**
     * Creates a new company with the form data.
     * Upon successful creation, the business user is registered.
     * Displays a success or error message based on the outcome.
     */
    createCompany() {
      this.$ApiService
        .create_company(this.company)
        .then((res) => {
          this.createBusinessUser(res.data.id);
        })
        .catch((error) => {
          this.alertVisible = true;
          this.alertType = "error";
          this.alertText = "Something went wrong, please try again!";
        });
    },

    /**
     * Creates a new business user with the form data after encrypting the password.
     * Sends a registration email to the business user.
     * Displays a success or error message based on the outcome.
     */
    createBusinessUser(companyId) {
      if (this.isPasswordValid) {
        this.business_user.companyId = companyId;
        let encryptedPassword = UserServices.encryptPassword(
          this.business_user.password,
        );
        let userWithEncryptedPassword = {
          ...this.business_user,
          password: encryptedPassword,
        };
        const emailData = {
          to: this.business_user.email,
          subject: "Registration to WorkNest",
          text: `Welcome ${this.business_user.name}, you've been registered as a business user for the company ${this.company.name}. \nUse the following code to login as a company: ${this.company.companyCode}. \nThen use the following password: ${this.business_user.password}`,
        };
        this.$ApiService
          .create_user(userWithEncryptedPassword)
          .then(() => {
            this.$ApiService
              .send_mail(emailData)
              .then(() => {
                this.alertVisible = true;
                this.alertType = "success";
                this.alertText = "Registration was successful!";
              })
              .catch((emailError) => {
                console.error("Error sending email:", emailError);
              });
          })
          .catch((error) => {
            this.alertVisible = true;
            this.alertType = "error";
            this.alertText = "Something went wrong, please try again!";
          });
      } else {
        alert("Passwords do not match!");
      }
    },

    /**
     * Clears the form and resets the validation state.
     * Hides the registration alert dialog.
     */
    clear() {
      this.alertVisible = false;
      this.company = { ...this.companyInitialState };
      this.business_user = { ...this.business_userInitialState };
      this.password_confirmed = "";
      this.isPasswordValid = false;
      this.v$.$reset();
    },
  },

  validations: {
    business_user: {
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
    company: {
      name: { required, minLength: minLength(3) },
      email: {
        required,
        email,
        isUnique: helpers.withMessage(
          "This email is already in use",
          helpers.withAsync(emailUsed),
        ),
      },
      vatCode: { required },
      phone: { required, numeric },
      companyCode: { required },
    },
  },
};
</script>
