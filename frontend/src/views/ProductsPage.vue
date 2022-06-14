<template>
  <div id="wrapper">
    <h1 id="productsHeader">Products</h1>
    <products :key="this.$store.state.uniqueProductKey"></products>
    <h2 id="addNew">Add new product</h2>
    <div class="addProductForm">
      <form class="form" @submit.prevent="submitForm">
        <label>Name:</label><br />
        <input type="text" required v-model="addingName" /><br />
        <label>Size:</label><br />
        <select v-model="addingSize">
          <option disabled value="">Select size...</option>
          <option value="Large">Large</option>
          <option value="Medium">Medium</option>
          <option value="Small">Small</option>
        </select>
        <br /><br />
        <label for="price">Price:</label><br />
        <input type="number" required v-model="addingPrice" /><br />
        <label>Category:</label><br />
        <select required v-model="addingCategoryID">
          <option disabled value="">Select a category...</option>
          <option
            v-for="category in categories"
            :key="category.id"
            :value="category.id"
          >
            {{ category.name }}
          </option>
        </select>
        <br /><br />
        <label for="image">Image:</label><br />
        <input required type="file" name="image" @change="handleFileUpload" />
        <br />
        <btn-styled type="submit">Submit</btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import BtnStyled from "../components/BtnStyled.vue";
import Products from "../components/Products.vue";
import axios from "axios";
export default {
  components: {
    BtnStyled,
    Products,
  },
  data() {
    return {
      uniqueProductKey: 0,
      addingName: "",
      addingPrice: "",
      addingSize: "",
      addingCategoryID: "",
      addingImage: null,
      categories: [],
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    submitForm() {
      const fd = new FormData();
      fd.append("name", this.addingName);
      fd.append("size", this.addingSize);
      fd.append("price", this.addingPrice);
      fd.append("category_id", this.addingCategoryID);
      fd.append("productImage", this.addingImage);
      axios
        .post("http://localhost:3000/api/products/addproduct", fd, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.uniqueProductKey++;
          this.$store.commit("increment");
          alert("Product added!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
    handleFileUpload(event) {
      this.addingImage = event.target.files[0];
    },
  },
  mounted() {
    const url = "http://localhost:3000/api/productCategory/";
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
};
</script>
<style scoped>
#productsHeader {
  position: absolute;
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  width: 30%;
  left: 30%;
  top: 1%;
}
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 80%;
  height: 175%;
  margin: 0px;
  top: 10%;
  margin-left: 10%;
  margin-right: 10%;
  border-style: outset;
  border-color: #a80000;
}

.addProductForm {
  font-family: "Bookerly";
  color: black;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  position: absolute;
  left: 55%;
  top: 22%;
  border-color: #a80000;
}
#addNew {
  position: absolute;
  left: 60%;
  top: 15%;
}
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
  margin-bottom: 1%;
}
select {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
  margin-bottom: 1%;
}
</style>
