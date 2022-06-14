import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BaseDialog from "./components/UI/BaseDialog.vue";
import BtnStyled from "./components/BtnStyled.vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faHome,
  faList,
  faArrowRight,
  faUser,
  faInbox,
  faUsers,
  faPlusCircle,
  faMinusCircle,
  faBuilding,
  faWallet,
  faLocationArrow,
  faMessage,
  faArrowRightToBracket,
  faArrowRightFromBracket,
  faClipboardList,
  faCartShopping,
  faArrowLeftLong,
  faUserGear,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import axios from "axios";

const app = createApp(App);
library.add(
  faHome,
  faUserGear,
  faList,
  faArrowLeftLong,
  faArrowRight,
  faUser,
  faInbox,
  faUsers,
  faPlusCircle,
  faMinusCircle,
  faWallet,
  faBuilding,
  faLocationArrow,
  faMessage,
  faArrowRightToBracket,
  faArrowRightFromBracket,
  faClipboardList,
  faCartShopping
);
app.use(store);
app.use(router);
app.component("FontAwesomeIcon", FontAwesomeIcon);
app.component("btn-styled", BtnStyled);
app.component("base-dialog", BaseDialog);

axios.defaults.baseURL = "http://localhost:3000/api/";
axios.defaults.withCredentials = true;

axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (err) => {
    const {
      config,
      response: { status },
    } = err;

    const originalRequest = config;
    if (status === 403) {
      store.commit("logout");
      router.push({ name: "Login" });
      return Promise.reject(false);
    }

    if (originalRequest.url.includes("http://localhost:3000/api/user/login")) {
      return Promise.reject(err);
    }
    if (status === 401) {
      router.push({ name: "Home" });
    }
  }
);

app.mount("#app");
