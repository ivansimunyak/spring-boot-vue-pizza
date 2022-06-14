<template>
  <h1>{{ statusMessage }}</h1>
  <table class="table">
    <thead>
      <tr>
        <th v-for="(header, index) in headers" :key="index">{{ header }}</th>
        <th>Remove</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(message, index) in messages" :key="index">
        <td v-for="(column, indexColumn) in columns" :key="indexColumn">
          {{ message[column] }}
        </td>
        <td v-if="checkDetails">
          <btn-styled
            @click="$router.push({ path: `/checkDetails/${order.id}` })"
            >Check Details</btn-styled
          >
        </td>
        <td>
          <btn-styled
            class="btnDelete"
            @click="removeMessage(message.id, index)"
            >Remove</btn-styled
          >
        </td>
      </tr>
    </tbody>
  </table>
</template>
<script>
import axios from "axios";
import BtnStyled from "./BtnStyled.vue";
export default {
  components: {
    BtnStyled,
  },
  data() {
    return {
      messages: [],
      headers: ["ID", "Name", "Content", "Email"],
      columns: ["id", "name", "content", "email"],
      statusMessage: "",
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
      "Bearer " + this.accessToken;
    const url = "http://localhost:3000/api/message/";
    axios
      .get(url)
      .then((response) => {
        this.messages = response.data;
      })
      .catch((error) => {
        console.log(error.response.status);
      });
  },
  methods: {
    removeMessage(id, index) {
      axios
        .post("http://localhost:3000/api/message/removemessage/" + id, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((res) => {
          this.statusMessage = "Message removed successfully! ";
          //Perform Success Action
          console.log(res.data);
          this.messages.splice(index, 1);
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
