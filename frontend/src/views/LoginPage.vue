<template>
  <div id="wrapper">
    <h1>Login</h1>
    <h2>{{ errorMsg }}</h2>
    <h2 v-if="userRegistered">You registered successfully!</h2>
    <form class="HomepageLoginForm" @submit.prevent="submitForm">
      <b><label>Username:</label></b
      ><br />
      <input
        id="username"
        type="text"
        name="username"
        v-model="username"
        required
      /><br />
      <b><label>Password:</label></b
      ><br />
      <input
        id="password"
        type="password"
        name="password"
        v-model="password"
        required
      /><br />
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>
<script>
import axios from "axios";
import { mapMutations } from "vuex";
import BtnStyled from "../components/BtnStyled.vue";
export default {
  components: {
    BtnStyled,
  },
  data() {
    return {
      userRegistered: this.$route.params.userAdded,
      username: "",
      password: "",
      errorMsg: "",
    };
  },
  methods: {
    ...mapMutations(["setUser", "setToken", "setAdmin", "setEmployee"]),
    submitForm() {
      axios.defaults.withCredentials = true;
      axios
        .post("http://localhost:3000/api/user/login", {
          username: this.username,
          password: this.password,
        })
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          const accessToken = res.data.accessToken;
          const user = res.data.user;
          const isAdmin = res.data.isAdmin;
          const isEmployee = res.data.isEmployee;
          this.setUser(user);
          this.setToken(accessToken);
          this.setAdmin(isAdmin);
          this.setEmployee(isEmployee);
          this.$router.push("/");
        })
        .catch((error) => {
          // error.response.status Check status code
          this.errorMsg = "Wrong email or password!";
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
.HomepageLoginForm {
  position: absolute;
  top: 40%;
  width: 100%;
  text-align: center;
  font-size: 15px;
}
h1 {
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
}
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 40%;
  height: 105%;
  margin: 5px;
  top: 7.5%;
  right: 2.5%;
  border-style: outset;
  border-color: #a80000;
}
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
label {
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
}
</style>
