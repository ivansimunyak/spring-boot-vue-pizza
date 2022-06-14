<template>
  <h1>{{ statusMsg }}</h1>
  <p class="error" v-if="error">{{ error }}</p>
  <table class="table">
    <thead>
      <tr>
        <th v-for="(header, index) in headers" :key="index">{{ header }}</th>
        <th v-if="checkDetails">Details</th>
        <th>Edit</th>
        <th>Remove</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(order, index) in orders" :key="index">
        <td v-for="(column, indexColumn) in columns" :key="indexColumn">
          {{ order[column] }}
        </td>
        <td v-if="checkDetails">
          <btn-styled
            @click="$router.push({ path: `/checkDetails/${order.id}` })"
            >Check Details</btn-styled
          >
        </td>
        <td>
          <btn-styled
            class="btnEdit"
            @click="
              $router.push({
                path: `/editorder/${order.id}/${order.adress}/${order.name}/${order.phone_number}/${order.order_status}`,
              })
            "
            >Edit</btn-styled
          >
        </td>
        <td>
          <btn-styled class="btnDelete" @click="removeOrder(order.id, index)"
            >Remove</btn-styled
          >
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import BtnStyled from "./BtnStyled.vue";
import axios from "axios";
export default {
  components: { BtnStyled },
  name: "Orders",
  data() {
    return {
      orders: [],
      columns: ["name", "order_status", "adress", "phone_number"],
      headers: ["Name", "Order Status", "Adress", "Phone"],
      error: "",
      checkDetails: true,
      statusMsg: "",
    };
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
      "Bearer " + this.accessToken;
    const url = "http://localhost:3000/api/orders/";
    axios
      .get(url)
      .then((response) => {
        this.orders = response.data;
      })
      .catch((error) => {
        // error.response.status Check status code
        console.log(error.response.status);
      });
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    removeOrder(id, index) {
      axios
        .post("http://localhost:3000/api/orders/removeorder/" + id, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.statusMsg = "Order removed successfully!";
          this.orders.splice(index, 1);
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
table {
  border-collapse: collapse;
  table-layout: fixed;
  margin: 25px 0;
  font-size: 0.9em;
  padding: 0;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  width: 85%;
}
tbody tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: 0.25em;
}
thead tr {
  background-color: #a80000;
  color: #ffffff;
  text-align: left;
  font-weight: bold;
}

table th,
table td {
  width: 15px;
  padding: 12px 15px;
  text-align: center;
}

table th {
  font-size: 12.5px;
  letter-spacing: 0.1em;
}
</style>
