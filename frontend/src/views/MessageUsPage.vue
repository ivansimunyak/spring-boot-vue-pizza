<template>
  <div id="wrapper">
    <h1>Contact Us!</h1>
    <h2>{{ status }}</h2>
    <form class="contact-us" @submit.prevent="submitForm">
      <b><label>Enter your email: </label></b><br />
      <input
        id="email"
        type="email"
        maxlength="49"
        v-model="email"
        placeholder="Email"
        required
      /><br /><br />
      <textarea
        v-model="message"
        maxlength="200"
        placeholder="Enter your message.."
        required
      ></textarea>
      <br /><br />
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>
<script>
import BtnStyled from "../components/BtnStyled.vue";
import { mapGetters } from "vuex";
import axios from "axios";
export default {
  components: {
    BtnStyled,
  },
  data() {
    return {
      email: "",
      message: "",
      user_id: "",
    };
  },
  computed: {
    ...mapGetters(["user"]),
  },
  methods: {
    submitForm() {
      axios.defaults.withCredentials = true;
      axios
        .post("http://localhost:3000/api/message/sendmessage", {
          content: this.message,
          email: this.email,
          user_id: this.user.user_id,
        })
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.email = "";
          this.message = "";
          this.status = "Message sent!";
        })
        .catch((error) => {
          // error.response.status Check status code
          //  console.log( error.response.status)
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
#wrapper {
  background-color: rgba(255, 255, 255, 0.85);
  text-align: center;
  position: absolute;
  width: 50%;
  height: 100%;
  top: 10%;
  left: 25%;
  border-style: outset;
  z-index: 9999;
}
form {
  position: relative;
  top: 20%;
}
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
textarea {
  position: relative;
  resize: none;
  padding: 5px;
  width: 50%;
  height: 125px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
</style>
