<template>
  <div id="wrapper">
    <div id="header">
      <h1>Order Details</h1>
      <h2>Order ID {{ orderID }}</h2>
      <p>{{ ordersProduct }}</p>
    </div>
    <btn-styled class="btnDelete" @click="removeOrder">Delete Order</btn-styled>
    <btn-styled
      class="btnEdit"
      @click="
        $router.push({
          path: `/editorder/${orderID}`,
        })
      "
    >
      Edit Order</btn-styled
    >
    <btn-styled class="btnBack" @click="$router.push({ path: `/orders` })"
      >Back to orders</btn-styled
    >
    <btn-styled id="btnCancel" @click="setToCanceled"
      >Set to canceled</btn-styled
    >
    <btn-styled id="btnProcessing" @click="setToProcessing"
      >Set to processing</btn-styled
    >
    <btn-styled id="btnDelivered" @click="setToDelivered"
      >Set to delivered</btn-styled
    >
    <div class="left-list">
      <ul>
        <li>Name: {{ orderDetails.firstName }}</li>
        <br />
        <li>Phone Number: {{ orderDetails.phoneNumber }}</li>
        <br />
        <li>Adress: {{ orderDetails.adress }}</li>
        <br />
        <li>Email: {{ userEmail }}</li>
      </ul>
    </div>
    <div class="right-list">
      <ul>
        <li>Order Received: {{ this.format_date(orderDetails.orderDate) }}</li>
        <br />
        <li>Order Sent: {{ this.format_date(orderDetails.orderDate) }}</li>
        <br/>
        <li>Order Status: {{ orderDetails.status }}</li>
        <br/>
        <li>Comments: {{ orderDetails.customerComment }}</li>
      </ul>
    </div>

    <section class="orders-table">
      <table class="table">
        <thead>
        <tr>
          <th>Name</th>
          <th>Size</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(orderProduct, index) in orderProducts" :key="index">
          <td>
            {{ orderProduct.product.name }}
          </td>
          <td>
            {{ orderProduct.product.size }}
          </td>
          <td>
            {{ orderProduct.quantity }}
          </td>
          <td>
            {{ orderProduct.product.price }}
          </td>
          <td>{{ orderProduct.quantity * orderProduct.product.price }}</td>
        </tr>
        </tbody>
      </table>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import BtnStyled from "./BtnStyled.vue";
import moment from "moment";

export default {
  props: { orderID: Number },
  components: { BtnStyled },
  data() {
    return {
      orderProducts: [],
      orderDetails: [],
      orderDate: 0,
      userEmail: null,
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
    username() {
      return this.$store.getters.username;
    },
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
        console.log(response.data);
        this.orderDetails = response.data;
        this.orderProducts = response.data.ordersProducts;
        this.userEmail = response.data.customer.email;
      });
    this.format_date(this.whenMade);
  },
  methods: {
    format_date(value) {
      if (value) {
        return moment(String(value)).format("MMMM Do YYYY, h:mm:ss a");
      }
    },
    removeOrder() {
      axios
        .post(
          "http://localhost:8080/api/orders/deleteorder/?id=" + this.orderID
        )
        .then(() => {
          //Perform Success Action
          alert("Order removed successfully! ");
          this.$router.push("/orders");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error);
        });
    },
    setToDelivered() {
      axios
        .post("http://localhost:8080/api/orders/updateorderstatus", {
          id: this.orderID,
          status: "Delivered",
        })
        .then(() => {
          //Perform Success Action
          this.$router.push({
            name: "Order Page",
            statusEdited: true,
          });
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error);
        });
    },
    setToCanceled() {
      axios
        .post("http://localhost:8080/api/orders/updateorderstatus", {
          id: this.orderID,
          status: "Canceled",
        })
        .then(() => {
          //Perform Success Action
          this.$router.push({
            name: "Order Page",
            statusEdited: true,
          });
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error);
        });
    },
    setToProcessing() {
      axios
        .post("http://localhost:8080/api/orders/updateorderstatus", {
          id: this.orderID,
          status: "Processing",
        })
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.$router.push({
            name: "Order Page",
            statusEdited: true,
          });
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error);
        });
    },
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
#btnDelivered {
  position: absolute;
  top: 5.5%;
  right: 10%;
  width: 15%;
  height: 5%;
}
#btnProcessing {
  position: absolute;
  top: 12%;
  right: 10%;
  width: 15%;
  height: 5%;
}
#btnCancel {
  position: absolute;
  top: 18%;
  right: 10%;
  width: 15%;
  height: 5%;
}

.btnBack {
  position: absolute;
  left: 6%;
  top: 25%;
  width: 20%;
}
.btnEdit {
  position: absolute;
  left: 35%;
  top: 5%;
  width: 30%;
  height: 7.5%;
}
.btnDelete {
  position: absolute;
  left: 35%;
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
}

ul {
  font-weight: 300;
}

.left-list {
  height: 18%;
  width: 40%;
  top: 35%;
  left: 10%;
  position: absolute;
  border-style: ridge;
  border-color: #a80000;
  background-color: white;
  overflow: hidden;
  padding: 5px;
  margin: 5px 0;
  border-radius: 15px;
}

.left-list ul {
  display: inline;
  font-size: 25px;
  margin-top: 1%;
  margin-bottom: 1%;
}
.right-list {
  top: 35%;
  right: 11%;
  position: absolute;
  background-color: white;
  width: 35%;
  border-style: ridge;
  border-color: #a80000;
  height: 18%;
  padding: 5px;
  margin: 5px 0;
  border-radius: 15px;
}
.right-list ul {
  margin-top: 1%;
  list-style-type: none;
  font-size: 25px;
  text-align: center;
}
li {
  border-bottom: 1px solid #a80000;
}
.orders-table {
  border: 1px solid #999;
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 250px;
  width: 40%;
  position: relative;
  top: 60%;
  margin-left: auto;
  margin-right: auto;
}
table {
  border-collapse: collapse;
  width: 100%;
}
th {
  position: sticky;
  top: 0;
  background: #a80000;
  padding: 10px 5px;
  text-align: center;
  border-bottom: 1px solid #a80000;
  color: white;
  z-index: 3;
}
td {
  padding: 5px 5px;
  text-align: center;
  z-index: 1;
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
  sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
}
</style>
