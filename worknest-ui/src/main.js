import "./assets/main.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import Api from "./services/api.services";

// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import { aliases, mdi } from "vuetify/iconsets/mdi";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

const vuetify = createVuetify({
  components,
  directives,
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: {
      mdi,
    },
  },
});

const app = createApp(App);
app.use(vuetify);
app.use(router);
app.config.globalProperties.$ApiService = Api;

app.mount("#app");
