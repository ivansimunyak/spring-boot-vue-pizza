<template>
  <div id="navlist">
    <router-link v-if="isAdmin" to="/admin">
      <font-awesome-icon :icon="['fas', 'user-gear']"/>
      Admin
    </router-link>
    <router-link v-if="isAdmin || isEmployee" to="/orders">
      <font-awesome-icon :icon="['fas', 'list']"/>
      Orders
    </router-link>
    <router-link v-if="isAdmin" to="/reviews">
      <font-awesome-icon :icon="['fas', 'user-gear']"/>
      Reviews
    </router-link>
    <router-link v-if="isAdmin" to="/products">
      <font-awesome-icon :icon="['fas', 'list']"/>
      Products
    </router-link>
    <router-link v-if="isAdmin" to="/categories">
      <font-awesome-icon :icon="['fas', 'list']"/>
      Categories
    </router-link>
    <router-link v-if="isAdmin" to="/usertypes">
      <font-awesome-icon :icon="['fas', 'users']"/>
      User Types
    </router-link>
    <router-link to="/">
      <font-awesome-icon :icon="['fas', 'house']"/>
      Home
    </router-link>
    <router-link to="/about">
      <font-awesome-icon :icon="['fas', 'users']"/>
      About Us
    </router-link>
    <router-link v-if="isLoggedIn" to="/profile">
      <font-awesome-icon :icon="['fas', 'user']"/>
      Profile
    </router-link>
    <router-link to="/cart"
    >
      <font-awesome-icon :icon="['fas', 'cart-shopping']"/>
      Cart ({{
        cartCount
      }})
    </router-link
    >
    <router-link v-if="isLoggedIn || isAdmin" to="/logout"
    >
      <font-awesome-icon :icon="['fas', 'arrow-right-from-bracket']"/>
      Logout
    </router-link
    >
    <router-link v-if="!isLoggedIn" to="/login"
      ><font-awesome-icon :icon="['fas', 'arrow-right-to-bracket']" />
      Login</router-link
    >
    <router-link v-if="!isLoggedIn" to="/register"
      ><font-awesome-icon :icon="['fas', 'clipboard-list']" />
      Register</router-link
    >
  </div>
  <router-view />
</template>
<script>
import {mapGetters} from "vuex";

export default {
  data() {
    return {
      isWorking: true,
    };
  },
  computed: {
    ...mapGetters(["isLoggedIn", "isAdmin", "cartCount", "isEmployee"]),
  },
};
</script>
<style>
body {
  background-image: url("assets/background.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  margin: 0;
}
#app {
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
#navlist {
  border-bottom: 1px solid #a80000;
  border-top: 1px solid #a80000;
  margin: 0 0 30px;
  text-align: justify-all;
  background-color: #a80000;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  padding: 13px 0;
}
a:link,
a:visited {
  border: 1px solid #a80000;
  padding: 1px 0.5em;
  color: #ffffff;
  font-weight: bold;
  text-decoration: none;
  font-size: 11.5px;
}
a:hover,
a:active,
a:focus {
  border: 1px solid #ffffff;
  padding: 1px 0.5em;
  text-decoration: none;
  background-color: #ca0000;
}
li {
  padding-right: 1px;
  display: inline;
  font-size: 0.6em;
  text-align: center;
}
ul {
  margin: 0;
  padding: 0;
  text-align: center;
}
a {
  background-color: #a80000;
}
</style>
