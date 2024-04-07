<script setup>
import { useVuelidate } from "@vuelidate/core";
import {
  required,
  email,
  minLength,
  maxLength,
  numeric,
} from "@vuelidate/validators";
</script>


<template>

    <v-form @submit.prevent="createUser()">

        <v-card class="mx-auto rounded-lg pa-12 mt-12" style="background-color: #3F51B50E;" max-width="90%"
            elevation="10">
            <p class="text-center text-h2 mb-3 font-weight-bold">Sign-Up</p>
            <div class="mb-5">
            <h2 class="text-h6 font-weight-thin d-inline mr-2">Do you have an account?</h2>
            
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
                    <v-text-field v-model="user.name" label="First name" variant="outlined"
                        color="indigo"></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">Surname</div>
                    <v-text-field v-model="user.surname" label="Last name" variant="outlined"
                        color="indigo"></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">Username</div>
                    <v-text-field v-model="user.username" label="Username" variant="outlined"
                        color="indigo"></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">E-mail</div>
                    <v-text-field v-model="user.email" label="E-mail" variant="outlined" color="indigo"
                        :error-messages="v$.user.email.$errors.map(e => e.$message)" @blur="v$.user.email.$touch"
                        @input="v$.user.email.$touch"
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">TAX Code</div>
                    <v-text-field v-model="user.taxCode" label="TAX Code" variant="outlined"
                        color="indigo"></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">Company code</div>
                    <v-text-field v-model="user.companyCode" label="Company code" variant="outlined"
                        color="indigo"></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">Password</div>
                    <v-text-field v-model="user.password" label="Password"
                        variant="outlined" color="indigo"
                        :type="passwordVisible ? 'text' : 'password'"
                        :append-inner-icon="passwordVisible ? 'mdi-eye-off' : 'mdi-eye'"
                        @click:append-inner="passwordVisible = !passwordVisible"
                        :error-messages="v$.user.password.$invalid ? 'Password must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 8 characters long' : [] " @blur="v$.user.password.$touch"
                        @input="v$.user.password.$touch">
                    </v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">Confirm password</div>
                    <v-text-field v-model="password_confirmed" label="Confirm Password"
                    :type="passwordConfirmedVisible ? 'text' : 'password'"
                    :append-inner-icon="passwordConfirmedVisible ? 'mdi-eye-off' : 'mdi-eye'"
                    @click:append-inner="passwordConfirmedVisible = !passwordConfirmedVisible"
                    :error-messages=" !isPasswordValid && v$.password_confirmed.$dirty ? 'Password are different!' : [] " @blur="v$.password_confirmed.$touch"
                        @input="validatePassword" variant="outlined" color="indigo"></v-text-field>
                </v-col>
                <v-col cols="12" md="4" class="d-flex justify-left align-self-center">
                    <v-switch label="Barrier free" v-model="user.barrerFreeFlag" color="blue-darken-3">
                        <template v-slot:label>
                            <div>
                                <v-icon icon="mdi-wheelchair-accessibility mx-2" size="x-large"></v-icon>
                                <p class="text-body-1 font-weight-bold d-inline">Barrier free</p>
                            </div>
                        </template>
                    </v-switch>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" class="d-flex justify-center align-self-center">
                    <v-btn :disabled="!isPasswordValid || v$.user.$invalid" prepend-icon="mdi-check" size="x-large" color="blue-darken-4"
                        type="submit">
                        <template v-slot:prepend>
                            <v-icon size="large" color="green-lighten-1"></v-icon>
                        </template>
                        Submit</v-btn>
                </v-col>
            </v-row>
        </v-card>
    </v-form>
</template>

<script>

import UserServices from "../services/user.services";
import { helpers } from "@vuelidate/validators";

const checkPassword = helpers.regex(
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&,-.#'^"£=(){}%€;:~<>+|/])[A-Za-z\d@$!%*?&,-.#'^"£=(){}%€;:~<>+|/]{8,30}$/
);

/**
* User Registration Form Component
* This component is used for the user registration process, including input fields for user details and password validation.

* @vue-data {Object} user - Contains fields like name, surname, email, username, password, taxCode, companyCode, type, and barrerFreeFlag.
* @vue-data {Boolean} isPasswordValid - Indicates whether the password matches the confirmed password.
* @vue-data {String} password_confirmed - Stores the password confirmation input by the user.

* @vue-method {Function} validatePassword - Validates that the user's password and confirmation password match, setting isPasswordValid accordingly.
* @vue-method {Function} createUser - Submits the user registration form if the password is validated, otherwise shows an alert.

* @vue-event {String} user-registration-submit - Emitted when the user submits the registration form and the password is validated.
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
        validatePassword() {
            this.isPasswordValid = this.user.password && this.user.password === this.password_confirmed;
        },
        createUser() {
            if (this.isPasswordValid) {
                let encryptedPassword = UserServices.encryptPassword(this.user.password);
                let userWithEncryptedPassword = {
                    ...this.user,
                    password: encryptedPassword
                };
                this.$ApiService.create_user(userWithEncryptedPassword).then(res => {
                    this.$router.push('/login')
                });
            } else {
                alert("Passwords do not match!");
            }
        }
    },
    validations: {
        user: {
          email: { required, email },
          password: { required, checkPassword },
        },
        password_confirmed: { required },
    }
}
</script>