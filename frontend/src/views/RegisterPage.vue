<template>
  <div id="wrapper">
    <h1>Register</h1>
    <h2>{{ errorMessage }}</h2>
    <form class="RegistrationPageForm" @submit.prevent="submitForm">
      <b><label>Enter your username:</label></b
      ><br />
      <input
        id="username"
        type="text"
        maxlength="25"
        name="username"
        v-model="addingUsername"
        required
      /><br />
      <b><label>Enter your name:</label></b
      ><br />
      <input
        id="name"
        type="text"
        maxlength="25"
        name="name"
        pattern="[A-z]+"
        v-model="addingName"
        required
      /><br />
      <b><label>Enter your email:</label></b
      ><br />
      <input
        id="email"
        type="email"
        maxlength="49"
        name="email"
        v-model="addingEmail"
        required
      /><br />
      <b><label>Enter your password:</label></b
      ><br />
      <input
        id="password"
        type="password"
        name="password"
        maxlength="25"
        v-model="addingPassword"
        required
      /><br />
      <b><label>Repeat your password:</label></b
      ><br />
      <input
        id="rptpassword"
        type="password"
        name="rptpassword"
        maxlength="25"
        v-model="rptPassword"
        required
      /><br />
      <b><label>Enter your adress: </label></b><br />
      <input
        id="adress"
        type="text"
        name="adress"
        maxlength="25"
        v-model="addingAdress"
        required
      /><br />
      <b><label>Enter your phone number:(+381) </label></b><br />
      <input
        id="phone"
        type="number"
        name="phone"
        max="2147483645"
        min="0"
        v-model="addingPhone"
        required
      /><br />
      <b><label>Choose your city: </label></b><br />
      <select required name="location_name" v-model="addingCity">
        <option disabled value="">Choose a city...</option>
        <option v-for="city in cities" :value="city.id" :key="city.id">
          {{ city.name }}
        </option></select
      ><br /><br />
      <btn-styled type="submit">Register</btn-styled>
    </form>
  </div>
</template>
<script>
import BtnStyled from "../components/BtnStyled.vue";
import axios from "axios";
export default {
  components: {
    BtnStyled,
  },
  data() {
    return {
      addingUsername: "",
      addingName: "",
      addingEmail: "",
      addingPassword: "",
      rptPassword: "",
      addingAdress: "",
      addingPhone: "",
      userType: [],
      errorMessage: "",
      cities: [],
      addingCity: "",
    };
  },
  methods: {
    submitForm() {
      if (this.addingPassword.length >= 8) {
        if (this.rptPassword == this.addingPassword) {
          axios
            .post("http://localhost:3000/api/user/register", {
              username: this.addingUsername,
              password: this.addingPassword,
              email: this.addingEmail,
              adress: this.addingAdress,
              name: this.addingName,
              phone_number: this.addingPhone,
              user_type_id: this.userType,
              city_id: this.addingCity,
            })
            .then((res) => {
              //Perform Success Action
              console.log(res.data.msg);
              if (res.data.msg == "Register success!") {
                this.$router.push({
                  name: "Login",
                  params: { userAdded: true },
                });
              } else if (res.data.msg == "Username or email taken!") {
                this.errorMessage = res.data.msg;
              }
            })
            .catch((error) => {
              // error.response.status Check status code
              console.log(error);
            });
        } else {
          this.errorMessage = "Passwords do not match";
        }
      } else {
        this.errorMessage = "Password needs to be over 8 characters";
      }
    },
  },
  mounted() {
    const url = "http://localhost:3000/api/city/foruser";
    axios.get(url).then((response) => {
      this.cities = response.data;
    });
    const url2 = "http://localhost:3000/api/userType/customertype";
    axios.get(url2).then((response) => {
      this.userType = response.data[0].id;
    });
  },
};
</script>
<style scoped>
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
label {
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
  font-size: 90%;
}
.RegistrationPageForm {
  position: absolute;
  top: 20%;
  width: 100%;
  text-align: center;
}
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
select {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
</style>
