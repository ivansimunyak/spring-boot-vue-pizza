<template>
  <h1>{{ this.statusMsg }}</h1>
  <base-dialog v-if="openDialog" title="Order Comment" @close="closeDialog">
    <template #default>
      <!-- Comment below -->
      <p>{{ featuredComment }}</p>
      <btn-styled @click="closeDialog">Ok</btn-styled>
    </template>
  </base-dialog>
  <table class="table">
    <thead>
    <tr>
      <th v-for="(header, index) in headers" :key="index">{{ header }}</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(review, index) in reviews" :key="index">
      <td>{{ review.order_id }}</td>
      <td>{{ review.reviewer.firstName }} {{ review.reviewer.lastName }}</td>
      <td>{{ review.reviewer.email }}</td>
      <td>{{ review.rating }}</td>
      <td>
        <btn-styled class="btnEdit" @click="checkComment(review.comment)"
        >Check Comment
        </btn-styled>
      </td>
      <td>
        <btn-styled
            class="btnDelete"
            @click="$router.push({ path: `/checkDetails/${review.order_id}` })"
        >Check Order
        </btn-styled>
      </td>
      <td>
        <btn-styled
            class="btnDelete"
            @click="this.removeReview(review.id, index)"
        >Remove
        </btn-styled>
      </td>
    </tr>
    </tbody>
  </table>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      headers: [
        "Order ID",
        "Name",
        "Email",
        "Rating",
        "Comment",
        "Check Order",
        "Remove",
      ],
      columns: ["order_id", "reviewer.firstName", "reviewer.email", "rating"],
      reviews: [],
      statusMsg: "",
      featuredComment: "",
      openDialog: false,
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    removeReview(id, index) {
      axios
          .post("http://localhost:8080/api/reviews/deletereview/?id=" + id, {
            headers: {
              Authorization: "Bearer " + this.accessToken,
            },
          })
          .then(() => {
            //Perform Success Action
            this.statusMsg = "Review removed successfully!";
            this.reviews.splice(index, 1);
          })
          .catch((error) => {
            // error.response.status Check status code
            console.log(error);
          });
    },
    checkComment(comment) {
      this.featuredComment = comment;
      this.openDialog = true;
    },
    closeDialog() {
      this.openDialog = false;
    },
  },
  mounted() {
    const url = "http://localhost:8080/api/reviews/";
    axios
        .get(url, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((response) => {
          this.reviews = response.data;
        });
  },
};
</script>

<style scoped>
dialog {
  position: fixed;
  top: 10vh;
  left: 45%;
  width: 80%;
  z-index: 100;
  border-radius: 6px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding: 0;
  margin: 0;
  overflow: hidden;
}

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
