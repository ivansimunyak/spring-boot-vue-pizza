<template>
  <div id="wrapper">
    <h1 id="head">Payment Methods</h1>
    <payment-methods :key="this.uniquePayKey" />
    <h2 id="addNew">Add new payment method</h2>
    <div class="addPayMethod">
      <form class="form" @submit.prevent="submitForm">
        <label for="fname">Name:</label><br />
        <input
          type="text"
          ref="inputName"
          id="name"
          name="name"
          required
          v-model="addingName"
        /><br />
        <btn-styled type="submit">Submit</btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import BtnStyled from "../components/BtnStyled.vue";
import PaymentMethods from "../components/PaymentMethods.vue";
export default {
  components: { BtnStyled, PaymentMethods },
  data() {
    return {
      addingName: "",
      uniquePayKey: 0,
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    submitForm() {
      axios
        .post(
          "http://localhost:3000/api/paymentMethod/addpaymentmethod",
          { name: this.addingName },
          {
            headers: {
              Authorization: "Bearer " + this.accessToken,
            },
          }
        )
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.uniquePayKey++;
          this.addingName = "";
          alert("Method added!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
  },
};
</script>
<style scoped>
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 80%;
  height: 100%;
  margin: 0px;
  top: 10%;
  margin-left: 10%;
  margin-right: 10%;
  border-style: outset;
  border-color: #a80000;
}
#head {
  position: absolute;
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  left: 30%;
  top: 1%;
}
.addPayMethod {
  font-family: "Bookerly";
  color: black;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  position: absolute;
  left: 50%;
  top: 30%;
  border-color: #a80000;
}
#addNew {
  position: absolute;
  left: 50%;
  top: 19%;
}
input[type="text"] {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
</style>
