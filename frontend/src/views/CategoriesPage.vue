<template>
  <div id="wrapper">
    <categories :key="this.uniqueCatKey"></categories>
    <h2 id="addNew">Add new category</h2>
    <div class="addCategoryForm">
      <form class="form" @submit.prevent="submitForm">
        <label for="fname">Name:</label><br />
        <input type="text" required v-model="addingName" /><br />
        <btn-styled type="submit">Submit</btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import BtnStyled from "../components/BtnStyled.vue";
import Categories from "../components/Categories.vue";
import axios from "axios";

export default {
  components: {
    BtnStyled,
    Categories,
  },
  data() {
    return {
      uniqueCatKey: 0,
      addingName: "",
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
              "http://localhost:8080/api/productcategory/addproductcategory",
              {name: this.addingName},
              {
                headers: {
                  Authorization: "Bearer " + this.accessToken,
                },
              }
          )
          .then(() => {
            this.uniqueCatKey++;
            this.addingName = "";
            alert("Category added!");
          })
        .catch((error) => {
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
  top: 10%;
  margin: 0 10%;
  border-style: outset;
  border-color: #a80000;
}

.addCategoryForm {
  font-family: "Bookerly", sans-serif;
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
  left: 55%;
  top: 19%;
}
input[type="text"] {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
</style>
