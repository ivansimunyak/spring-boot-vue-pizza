<template>
  <div id="wrapper">
    <div id="header">
      <h1>Order Details</h1>
      <h2>Order ID {{ this.$route.params.id }}</h2>
    </div>

    <btn-styled class="btnBack" @click="$router.push({ path: `/profile` })"
      >Back to profile</btn-styled
    >
    <div class="left-list">
      <ul v-for="item in orderDetails" :key="item.id">
        <li>Name: {{ item.name }}</li>
        <br />
        <li>Phone Number: {{ item.phone_number }}</li>
        <br />
        <li>Store: {{ locationName }}</li>
        <br />
        <li>Adress: {{ item.adress }}</li>
        <br />
        <li>Payment Method: {{ paymentMethodName }}</li>
      </ul>
    </div>
    <div class="right-list">
      <ul v-for="item in orderDetails" :key="item.id">
        <li>Order Received: {{ this.format_date(this.whenMade) }}</li>
        <br />
        <li>Order Sent: {{ this.format_date(this.whenSent) }}</li>
        <br />
        <li>Order Status: {{ item.order_status }}</li>
        <br />
        <li>Employee:{{ employeeName }}</li>
        <br />
        <li>Comments: {{ item.comments }}</li>
      </ul>
    </div>

    <table-orders
      id="table"
      :columns="this.columns"
      :headers="this.headers"
      :items="orderProducts"
      :columnsX="this.columnsX"
    ></table-orders>
  </div>
</template>
<script>
import TableOrders from "./TableOrders.vue";
import axios from "axios";
import BtnStyled from "./BtnStyled.vue";
import moment from "moment";
export default {
  components: { TableOrders, BtnStyled },
  data() {
    return {
      columns: ["name", "size", "quantity", "price"],
      headers: ["Product Name", "Product Size", "Quantity", "Price"],
      columnsX: ["quantity", "price"],
      orderProducts: [],
      orderDetails: [],
      sum: 0,
      paymentMethodID: "",
      paymentMethodName: "",
      locationID: "",
      locationName: "",
      employeeID: "",
      employeeName: "",
      whenMade: 0,
      whenSent: "",
      orderID: 0,
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    format_date(value) {
      if (value) {
        return moment(String(value)).format("MMMM Do YYYY, h:mm:ss a");
      }
    },
  },
  mounted() {
    this.orderID = this.$route.params.id;
    const url1 = "http://localhost:3000/api/orders/details/" + this.orderID;
    axios
      .get(url1, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.orderDetails = response.data;
        this.paymentMethodID = response.data[0].payment_method_id;
        this.locationID = response.data[0].location_id;
        this.employeeID = response.data[0].employee_id;
        this.whenMade = new Date(response.data[0].when_made);
        this.adress = response.data[0].adress;
        this.name = response.data[0].name;
        this.phone_number = response.data[0].phone_number;
        this.orderStatus = response.data[0].order_status;
        this.whenSent = new Date(response.data[0].when_sent);
        const url5 =
          "http://localhost:3000/api/user/oneuser/" + this.employeeID;
        axios.get(url5).then((response) => {
          this.employeeName = response.data[0].user_name;
        });
      });
    const url4 =
      "http://localhost:3000/api/location/foruser/" + this.locationID;
    axios
      .get(url4, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        console.log(response.data);
        this.locationName = response.data[0].locationName;
      });

    const url3 =
      "http://localhost:3000/api/paymentMethod/forall/" + this.paymentMethodID;
    axios
      .get(url3, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.paymentMethodName = response.data[0].name;
      });
    const url = "http://localhost:3000/api/orders/" + this.orderID;
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.orderProducts = response.data;
      });
  },
};
</script>
<style scoped>
#header {
  position: absolute;
  font-family: "Times New Roman", Times, serif;
  color: black;
  border-style: double;
  width: 30%;
  left: 1%;
  top: 1%;
  background-color: rgb(255, 255, 255);
}
.btnBack {
  position: absolute;
  left: 6%;
  top: 25%;
  width: 20%;
}
.btnEdit {
  position: absolute;
  left: 55%;
  top: 5%;
  width: 30%;
  height: 7.5%;
}
.btnDelete {
  position: absolute;
  left: 55%;
  top: 15%;
  width: 30%;
  height: 7.5%;
}
#wrapper {
  background-color: rgba(255, 255, 255, 0.85);
  position: absolute;
  width: 93%;
  height: 130%;
  top: 10%;
  left: 3.25%;
  border-style: outset;
  /* filter: blur(1px); */
}
.wrap-table {
  position: absolute;
  top: 65%;
  left: -1%;
}
ul {
  font-weight: 300;
}
#table {
  width: 76%;
  border-style: outset;
  /* border-top-style:    ; */
  border-color: #a80000;
}
.left-list {
  height: 18%;
  width: 40%;
  top: 35%;
  left: 5%;
  position: absolute;
  border-style: ridge;
  border-color: #a80000;
  background-color: white;
  overflow: hidden;
  padding: 5px;
  margin: 5px 0;
  border-radius: 15px;
  box-shadow: 5px;
}

.left-list ul {
  display: inline;
  font-size: 25px;
  margin-top: 1%;
  margin-bottom: 1%;
}
.right-list {
  top: 35%;
  right: 5%;
  position: absolute;
  background-color: white;
  height: 18%;
  width: 40%;
  border-style: ridge;
  border-color: #a80000;
  padding: 5px;
  margin: 5px 0;
  border-radius: 15px;
  box-shadow: 5px;
}
.right-list ul {
  margin-top: 1%;
  list-style-type: none;
  text-align: center;
  font-size: 25px;
}
li {
  border-bottom: 1px solid #a80000;
}
</style>
