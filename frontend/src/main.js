import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BaseDialog from "./components/UI/BaseDialog.vue";
import BtnStyled from "./components/BtnStyled.vue";
import {library} from "@fortawesome/fontawesome-svg-core";

import {
  faArrowLeft,
  faArrowLeftLong,
  faArrowRight,
  faArrowRightFromBracket,
  faArrowRightToBracket,
  faBuilding,
  faCartShopping,
  faClipboardList,
  faHome,
  faInbox,
  faList,
  faLocationArrow,
  faMessage,
  faMinusCircle,
  faPlusCircle,
  faUser,
  faUserGear,
  faUsers,
  faWallet,
} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import axios from "axios";
import {initializeApp} from "firebase/app";
import {getFirestore} from "firebase/firestore/lite";
import firebase from "firebase/compat/app";
import "firebase/compat/storage";

const app = createApp(App);
library.add(
    faHome,
    faUserGear,
    faList,
    faArrowLeftLong,
    faArrowLeft,
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
      response: {status},
    } = err;

    const originalRequest = config;
    if (status === 403) {
      store.commit("logout");
      router.push({name: "Login"});
      return Promise.reject(false);
    }
    if (status === 500) {
      return Promise.reject(err.response.data.message);
    }

    if (originalRequest.url.includes("http://localhost:8080/login")) {
      console.log("done");
      return Promise.reject(err);
    }
    if (status === 401) {
      router.push({name: "Home"});
    }
  }
);
const firebaseConfig = {
  apiKey: "AIzaSyCtBbe5k-mUJLLGU1MmwsaHSgqR8cQPbJ8",

  authDomain: "pizza-place-cd744.firebaseapp.com",

  projectId: "pizza-place-cd744",

  storageBucket: "pizza-place-cd744.appspot.com",

  messagingSenderId: "699075204041",

  appId: "1:699075204041:web:8dc9144d308417de448e18",

  measurementId: "G-KTDXT03H1M",
};

firebase.initializeApp(firebaseConfig);
const app1 = initializeApp(firebaseConfig);
const db = getFirestore(app1);
var storage = firebase.storage();
export {storage, db};
app.mount("#app");
