<template>
  <div id="wrapper">
    <h1 id="productsHeader">Products{{ setImages }}</h1>
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
        <br/><br/>
        <label for="image">Image:</label><br/>
        <input type="file" @change="handleUpload($event)"/>
        <br/>
        <btn-styled @click.prevent="submitData()" class="btn btn-primary"
        >Add
        </btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import BtnStyled from "../components/BtnStyled.vue";
import Products from "../components/Products.vue";
import axios from "axios";
import {storage} from "../main";
import "firebase/storage";

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
      file: null,
      imageData: null,
      categories: [],
      addingCategoryName: "",
      setImages: [],
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    handleUpload(event) {
      console.log(event.target.files[0]);
      const fileType = event.target.files[0].type;
      if (fileType === "image/jpeg" || fileType === "image/png") {
        this.file = event.target.files[0];
        this.error = null;
      } else {
        this.error = "File type not valid";
        this.file = null;
        return;
      }
      const reader = new FileReader();
      reader.readAsDataURL(this.file);
      reader.onload = (e) => {
        this.imageData = e.target.result;
        // console.log(e.target.result)
      };
    },
    async submitData() {
      try {
        const refImage = storage
            .ref()
            .child("images")
            .child(this.file.name + Date.now());
        const res = await refImage.put(this.file);
        console.log(res);
        const urlImage = await refImage.getDownloadURL();
        console.log(urlImage);
        const record = this.categories.find(
            (element) => element.id === this.addingCategoryID
        );
        axios
            .post(
                "http://localhost:8080/api/product/addproduct",
                {
                  name: this.addingName,
                  size: this.addingSize,
                  price: this.addingPrice,
                  productCategory: {id: this.addingCategoryID, name: record.name},
                  imageUrl: urlImage,
                },
                {
                  headers: {
                    Authorization: "Bearer " + this.accessToken,
                  },
                }
            )
            .then(() => {
              //Perform Success Action
              this.uniqueProductKey++;
              this.$store.commit("increment");
              alert("Product added!");
            })
            .catch((error) => {
              // error.response.status Check status code
              console.log(error.response.status);
            });
      } catch (error) {
        console.log(error);
      }
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
  border-radius: 10px;
  border-width: 1px;
  margin: 5px 0 1%;
}
select {
  padding: 5px;
  border-radius: 10px;
  border-width: 1px;
  margin: 5px 0 1%;
}
</style>
