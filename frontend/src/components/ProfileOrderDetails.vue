<template>
  <div id="wrapper">
    <div id="header">
      <h1>Order Details</h1>
      <h2>Order ID {{ this.$route.params.id }}</h2>
    </div>
    <btn-styled
        v-if="
        orderDetails.status === 'Delivered' ||
        orderDetails.status === 'Canceled'
      "
        id="btnReview"
        @click="leaveReview = true"
    >Leave a review
    </btn-styled>
    <base-dialog v-if="leaveReview" title="Review" @close="closeDialog">
      <template #default>
        <!-- leave review below -->
        <h1>{{ errorMessage }}</h1>
        <form @submit.prevent="submitReview">
          <label id="rating">Select rating:</label><br/><br/>
          <select id="rating" v-model="userRating">
            <option>Excellent</option>
            <option>Very Good</option>
            <option>Okay</option>
            <option>Decent</option>
            <option>Bad</option>
          </select
          ><br/><br/>
          <label>Your comment:</label><br/><br/>
          <textarea required v-model="userComment" maxlength="499"></textarea>
          <br/>
          <btn-styled type="submit">Submit</btn-styled>
        </form>
      </template>
    </base-dialog>
    <btn-styled class="btnBack" @click="$router.push({ path: `/profile` })"
    >Back to profile
    </btn-styled>
    <div class="left-list">
      <ul>
        <li>Name: {{ orderDetails.firstName }}</li>
        <br/>
        <li>Phone Number: {{ orderDetails.phoneNumber }}</li>
        <br/>
        <li>Adress: {{ orderDetails.adress }}</li>
        <br/>
        <li>Email: {{ userEmail }}</li>
      </ul>
    </div>
    <div class="right-list">
      <ul>
        <li>Order Received: {{ this.format_date(orderDetails.orderDate) }}</li>
        <br/>
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
  components: {BtnStyled},
  data() {
    return {
      orderProducts: [],
      orderDetails: [],
      orderDate: 0,
      userEmail: null,
      leaveReview: false,
      errorMessage: "",
      userRating: "Okay",
      userComment: "",
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
    closeDialog() {
      this.leaveReview = false;
    },
    submitReview() {
      axios
          .post(
              "http://localhost:8080/api/reviews/addreview",
              {
                rating: this.userRating,
                comment: this.userComment,
                reviewer: {id: this.orderDetails.customer.id},
                order_id: this.$route.params.id,
              },
              {
                headers: {
                  Authorization: "Bearer " + this.accessToken,
                },
              }
          )
          .then(() => {
            //Perform Success Action
            this.closeDialog();
            alert("Review left successfully!");
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
  mounted() {
    this.orderID = this.$route.params.id;
    const url = "http://localhost:8080/api/orders/getorder/?id=" + this.orderID;
    axios
        .get(url, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
      .then((response) => {
        this.orderDetails = response.data;
        this.orderProducts = response.data.ordersProducts;
        this.userEmail = response.data.customer.email;
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

#wrapper {
  background-color: rgba(255, 255, 255, 0.85);
  position: absolute;
  width: 93%;
  height: 130%;
  top: 10%;
  left: 3.25%;
  border-style: outset;
}

#btnReview {
  position: relative;
  width: 25%;
  height: 8%;
  top: 5%;
  left: 10%;
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

select {
  padding: 3px;
  margin: 3px 0;
  border-radius: 10px;
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
  border-width: 1px;
}
</style>
