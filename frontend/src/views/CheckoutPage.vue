<template>
  <div class="info-form">
    <h1>Checkout</h1>
    <form @submit.prevent="submitForm">
      <div id="left-form">
        <input
            type="text"
            placeholder="Adress"
            v-model="userAdress"
            name="adress"
            maxlength="44"
        /><br/>
        <input
            type="number"
            placeholder="Phone Number"
            v-model="userPhone"
            name="phone"
            max="2147483645"
            min="0"
        /><br/>
        <input
            type="text"
            placeholder="First Name"
            v-model="firstName"
            name="first-name"
            maxlength="44"
        /><br/>
        <input
            type="text"
            placeholder="Last Name"
            v-model="lastName"
            name="last-name"
            maxlength="44"
        /><br/>
      </div>
      <div id="right-form">
        <textarea
          v-model="userComments"
          placeholder="Enter additional information"
          name="comments"
          maxlength="144"
        ></textarea>
      </div>
      <btn-styled id="order-btn">Order</btn-styled>
    </form>
  </div>
  <section class="productsTable">
    <table>
      <thead>
        <tr class="total">
          <th>Total: {{ total }}$</th>
        </tr>

        <tr>
          <th>Image</th>
          <th>Name</th>
          <th>Size</th>
          <th>Category</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Subtotal</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(product, index) in products" :key="index">
          <td>
            <img id="product-image" :src="product.imageUrl"/>
          </td>
          <td>{{ product.name }}</td>
          <td>{{ product.size }}</td>
          <td>{{ product.productCategory.name }}</td>
          <td>{{ product.price }}</td>
          <td>{{ product.quantity }}</td>
          <td>{{ product.price * product.quantity }}</td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      userAdress: "",
      userPhone: "",
      userComments: "",
      orderID: "",
      cartProducts: [],
      user: {},
    };
  },
  computed: {
    products() {
      return this.$store.getters.cart;
    },
    total() {
      return this.$store.getters.cartTotal;
    },
    username() {
      return this.$store.getters.username;
    },
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  mounted() {
    if (this.products.length > 0) {
      if (this.$store.getters.username != null) {
        axios.defaults.headers.common["Authorization"] =
            "Bearer " + this.accessToken;
        const url =
            "http://localhost:8080/api/user/getuser/?username=" +
            this.$store.getters.username;
        axios.get(url).then((response) => {
          this.user = response.data;
          this.userAdress = response.data.adress;
          this.userPhone = response.data.phoneNumber;
          this.firstName = response.data.firstName;
          this.lastName = response.data.lastName;
        });
      }
    } else {
      this.$router.push("/");
    }
  },
  methods: {
    submitForm() {
      if (this.$store.getters.username != null) {
        axios.defaults.headers.common["Authorization"] =
            "Bearer " + this.accessToken;
        axios

            .post("http://localhost:8080/api/orders/addorder", {
              customer: {id: this.user.id},
              adress: this.userAdress,
              phoneNumber: this.userPhone,
              customerComment: this.userComments,
              firstName: this.firstName,
              lastName: this.lastName,
            })
          .then((response) => {
            console.log(response);
            return axios.get(
                "http://localhost:8080/api/orders/getlatestorder/?id=" +
                this.user.id
            );
          })
          .then((response) => {
            this.orderID = response.data.id;
            this.products.forEach((product) => {
              product["order_id"] = response.data.id;
            });
            for (let i = 0; i < this.products.length; i++) {
              this.cartProducts.push({
                ordersProductId: {
                  ordersId: this.products[i].order_id,
                  productId: this.products[i].id,
                },
                quantity: this.products[i].quantity,
              });
            }
            return axios.post(
                "http://localhost:8080/api/ordersproduct/addordersproduct",
                this.cartProducts
            );
          })
          .then((response) => {
            if (response.status === 200) {
              this.$store.commit("resetCart");
              this.$router.push({path: `/profileorder/${this.orderID}`});
            }
          })
          .catch((error) => console.log(error));
      } else {
        let guestID = 9999;
        delete axios.defaults.headers.common["Authorization"];
        axios
            .post("http://localhost:8080/api/orders/addorder", {
              customer: {id: guestID},
              adress: this.userAdress,
              phoneNumber: this.userPhone,
              customerComment: this.userComments,
              firstName: this.firstName,
              lastName: this.lastName,
            })
            .then(() => {
              return axios.get(
                  "http://localhost:8080/api/orders/getlatestorder/?id=" + guestID
              );
            })
          .then((response) => {
            this.orderID = response.data.id;
            this.products.forEach((product) => {
              product["order_id"] = response.data.id;
            });
            for (let i = 0; i < this.products.length; i++) {
              this.cartProducts.push({
                ordersProductId: {
                  ordersId: this.products[i].order_id,
                  productId: this.products[i].id,
                },
                quantity: this.products[i].quantity,
              });
            }
            return axios.post(
                "http://localhost:8080/api/ordersproduct/addordersproduct",
                this.cartProducts
            );
          })
          .then((response) => {
            if (response.status === 200) {
              this.$store.commit("resetCart");
              this.$router.push({path: `/`});
            }
          })
          .catch((error) => console.log(error));
      }
    },
  },
};
</script>
<style scoped>
#order-btn {
  position: relative;
  margin-top: 20%;
  width: 200px;
}
.info-form {
  background-color: #ffffffd9;
  position: relative;
  width: 80%;
  height: 400px;
  top: 10%;
  margin: 0px 10%;
  border-style: outset;
  border-color: #a80000;
}
#left-form {
  position: absolute;
  margin-left: 20%;
}
#right-form {
  position: absolute;
  margin-left: 50%;
  margin-top: 2%;
}
.productsTable {
  border-collapse: collapse;
  table-layout: fixed;
  color: #333;
  font-size: 0.9em;
  padding: 0;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  text-align: center;
  margin: 25px auto;
  border-color: #a80000;
  width: 85%;
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
  background: white;
}
img {
  width: 125px;
}
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
select {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
  width: 220px;
}
label {
  font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
}
textarea {
  position: relative;
  resize: none;
  padding: 5px;
  width: 300px;
  height: 125px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
</style>
