<template>
  <div class="wrapper">
    <div class="header">
      <h1>Edit Order ID {{ orderID }}</h1>
      <h2>{{ formStatus }}</h2>
    </div>
    <btn-styled
        @click="$router.push({ path: `/checkDetails/${orderID}` })"
        class="backBtn"
    >
      <font-awesome-icon class="fa" icon="arrow-left-long"/>
      Details
    </btn-styled>
    <btn-styled @click="$router.push({ path: `/orders` })" class="homeBtn">
      <font-awesome-icon class="fa" icon="arrow-left-long"/>
      Orders
    </btn-styled>
    <div class="form">
      <form @submit.prevent="submitForm">
        <label for="fname">Name:</label><br/>
        <input
            type="text"
            required
            name="name"
            v-model="userData.firstName"
        /><br/>
        <label for="lname">Adress:</label><br/>
        <input
            type="text"
            required
            name="adress"
            v-model="userData.adress"
        /><br/>
        <label for="phone">Phone:</label><br/>
        <input
            type="number"
            required
            name="phone_number"
            v-model="userData.phoneNumber"
        /><br/>
        <label for="orderState">Order status:</label><br/>
        <select
            id="selectForm"
            required
            name="order_status"
            v-model="userData.status"
        >
          <option value="Delivered">Delivered</option>
          <option value="Canceled">Canceled</option>
          <option value="Processing">Processing</option>
        </select
        ><br/><br/>
        <btn-styled type="submit">Submit</btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import BtnStyled from "./BtnStyled.vue";

export default {
  components: {
    BtnStyled,
  },
  props: ["orderID"],
  data() {
    return {
      userData: [],
      formStatus: "",
      resetName: "",
    };
  },
  mounted() {
    const url = "http://localhost:8080/api/orders/getorder/?id=" + this.orderID;
    axios
        .get(url, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((response) => {
          this.userData = response.data;
        });
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    resetData() {
      this.forms = {
        name: "",
        adress: "",
        phone_number: "",
        order_status: "Delivered",
        id: this.orderID,
      };
    },
    submitForm() {
      axios
        .post("http://localhost:8080/api/orders/updateorder", this.userData)
        .then(() => {
          //Perform Success Action
          this.formStatus = "Order edited successfully!";
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
      this.resetData();
    },
  },
};
</script>
<style scoped>
h1 {
  font-family: "Times New Roman", Times, serif;
  color: black;
}
*,
*:before,
*:after {
  box-sizing: border-box;
}
*:focus {
  outline: none !important;
}
.wrapper {
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
.fa {
  transform: scale(1, 1.5);
}
.form {
  top: 15%;
  position: relative;
}
input[type="text"] {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
input[type="number"] {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
select {
  width: 27.5%;
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
.backBtn {
  position: absolute;
  left: 5%;
  top: 10%;
  width: 12.5%;
}
.homeBtn {
  position: absolute;
  left: 5%;
  top: 20%;
  width: 13%;
}
</style>
