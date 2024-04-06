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
                    <v-text-field v-model="user.email" label="E-mail" variant="outlined" color="indigo"></v-text-field>
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
                    <v-text-field v-model="user.password" :type="'password'" label="Password" @input="validatePassword"
                        variant="outlined" color="indigo"></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                    <div class="text-overline text-large-emphasis">Confirm password</div>
                    <v-text-field v-model="password_confirmed" :type="'password'" label="Confirm Password"
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
                    <v-btn :disabled="!isPasswordValid" prepend-icon="mdi-check" size="x-large" color="blue-darken-4"
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
            password_confirmed: ""
        };
    },
    methods: {
        validatePassword() {
            this.isPasswordValid = this.user.password && this.user.password === this.password_confirmed;
        },
        createUser() {
            if (this.isPasswordValid) {
                console.log(this.user);
                this.$ApiService.create_user(this.user).then(res => {
                    console.log(res);
                });
            } else {
                alert("Passwords do not match!");
            }
        }
    }
}
</script>


<style>
.main-content {
    background-color: var(--v-primary-base);
}

.header-content {
    color: var(--v-text-primary);
}

.color-box {
    width: 200px;
    height: 50px;
    margin-bottom: 10px;
    border: 1px solid #000;
    padding: 10px;
    margin-left: auto;
    margin-right: auto;
    background-color: var(--v-primary-base);
    color: #FFF;
}

.color-box.header {
    background-color: #3F51B5;
}

.color-box.navigation {
    background-color: #3F51B5;
}

.color-box.footer {
    background-color: #3F51B5;
}

.color-box.primary {
    background-color: #3F51B5;
}

.color-box.secondary {
    background-color: #283593;
}

.color-box.error {
    background-color: #E53935;
}

.color-box.warning {
    background-color: #FDD835;
}

.color-box.info {
    background-color: #E3F2FD;
}

.color-box.success {
    background-color: #43A047;
}

.checkbox-centered {
    margin: auto;
}
</style>