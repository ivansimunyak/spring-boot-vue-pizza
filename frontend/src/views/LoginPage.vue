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
    ...mapMutations(["setUsername", "setToken", "setAdmin", "setEmployee"]),
    submitForm() {
      axios
        .post("http://localhost:8080/login", {
          username: this.username,
          password: this.password,
        })
        .then((res) => {
          console.log(res.data.authorities[0].authority);
          const accessToken = res.data.jwttoken;
          const username = res.data.username;
          if (res.data.authorities[0].authority === "ROLE_Admin") {
            this.setAdmin(true);
          } else if (res.data.authorities[0].authority === "ROLE_Employee") {
            this.setEmployee(true);
          }
          this.setUsername(username);
          this.setToken(accessToken);
          this.$router.push("/");
        })
        .catch((error) => {
          this.errorMsg = "Wrong email or password!";
          console.log(error + "hey");
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
  border-width: 1px;
}
label {
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
}
</style>
