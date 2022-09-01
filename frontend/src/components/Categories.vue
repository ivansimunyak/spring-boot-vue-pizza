<template>
  <h1 id="categoriesHeader">Categories</h1>

  <section class="categoriesTable">
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Edit</th>
          <th>Remove</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(category, index) in categories" :key="index">
          <td>{{ category.name }}</td>
          <td>
            <btn-styled
              class="btnEdit"
              @click="editCategory(category.id, category.name, index)"
              >Edit</btn-styled
            >
          </td>
          <td>
            <btn-styled
              class="btnDelete"
              @click="removeCategory(category.id, index)"
              >Remove</btn-styled
            >
          </td>
        </tr>
      </tbody>
    </table>
  </section>
  <h2 id="editCategory">Edit category</h2>
  <div class="edit">
    <form class="form" @submit.prevent="editCategoryForm">
      <label for="id">ID:</label><br />
      <input type="number" required name="editID" v-model="editID" /><br />
      <label for="fname">Name:</label><br />
      <input type="text" required name="editName" v-model="editName" /><br />
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import BtnStyled from "./BtnStyled.vue";

export default {
  components: { BtnStyled },
  data() {
    return {
      categories: [],
      editName: "",
      editID: "",
      saveIndex: "",
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  mounted() {
    const url = "http://localhost:8080/api/productcategory";
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.categories = response.data;
      });
  },
  methods: {
    removeCategory(id, index) {
      axios
        .post(
            "http://localhost:8080/api/productcategory/deleteproductcategory/",
            {id: id},
            {
              headers: {
                Authorization: "Bearer " + this.accessToken,
              },
            }
        )
        .then((res) => {
          //Perform Success Action
          console.log(res);
          this.categories.splice(index, 1);
          alert("Category removed!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error);
        });
    },
    editCategoryForm() {
      axios
          .post(
              "http://localhost:8080/api/productcategory/updateproductcategory",
              {id: this.editID, name: this.editName},
              {
                headers: {
                  Authorization: "Bearer " + this.accessToken,
                },
              }
          )
          .then(() => {
            //Perform Success Action
            this.categories.splice(this.saveIndex, 1, {
              name: this.editName,
              id: this.editID,
            });
            alert("Category edited successfully!");
          })

        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
    editCategory(id, name, index) {
      this.editName = name;
      this.editID = id;
      this.saveIndex = index;
    },
  },
};
</script>

<style scoped>
#categoriesHeader {
  position: absolute;
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  width: 30%;
  left: 30%;
  top: 1%;
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

#editCategory {
  position: absolute;
  top: 45%;
  width: 30%;
  margin-left: 25%;
  left: 25%;
}
.edit {
  position: absolute;
  top: 55%;
  left: 50%;
  color: black;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  border-color: #a80000;
}
.categoriesTable {
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 250px;
  width: 40%;
  position: relative;
  top: 30%;
  margin-left: 2.5%;
  border-color: #a80000;
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
