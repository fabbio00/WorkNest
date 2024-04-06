<template>
    <div class="main-content">
      <h1 class="text-indigo-lighten-2">Do you have an account?</h1>
      <p>
        <a href="/login">
          <v-icon icon="mdi-logout"></v-icon>
          <span>Go to login page</span>
        </a>
      </p>
  
      <v-form @submit.prevent="createUser()">
        <v-container>
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field v-model="user.name" label="First name"></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-text-field v-model="user.surname" label="Surname"></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-text-field v-model="user.email" label="E-mail"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field v-model="user.username" label="Username"></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-text-field v-model="user.taxCode" label="TAX Code"></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-text-field v-model="user.companyCode" label="Company-code"></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field 
                v-model="user.password" 
                :type="'password'" 
                label="Password" 
                @input="validatePassword"></v-text-field>
            </v-col>
            <v-col cols="12" md="4">
              <v-text-field 
                v-model="password_confirmed"
                :type="'password'" 
                label="Confirm Password" 
                @input="validatePassword"></v-text-field>
            </v-col>
            <v-col cols="12" md="4" class="d-flex justify-center align-self-center">
              <v-checkbox label="Barrier free" v-model="user.barrerFreeFlag" class="checkbox-centered"></v-checkbox>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12" class="d-flex justify-center align-self-center">
              <v-btn :disabled="!isPasswordValid" class="color-box secondary" type="submit">Submit</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </div>
  </template>
  
  <script>
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

.color-box.header { background-color: #3F51B5; }
.color-box.navigation { background-color: #3F51B5; }
.color-box.footer { background-color: #3F51B5; }
.color-box.primary { background-color: #3F51B5; }
.color-box.secondary { background-color: #283593; }
.color-box.error { background-color: #E53935; }
.color-box.warning { background-color: #FDD835; }
.color-box.info { background-color: #E3F2FD; }
.color-box.success { background-color: #43A047; }

.checkbox-centered {
    margin: auto;
}
</style>