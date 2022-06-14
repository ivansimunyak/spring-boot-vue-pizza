<template>
  <section class="productsTable">
    <table>
      <thead>
        <tr>
          <th>Image</th>
          <th>Name</th>
          <th>Size</th>
          <th>Price</th>
          <th>Edit</th>
          <th>Remove</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(product, index) in products" :key="index">
          <td>
            <img
              id="product-image"
              :src="require(`../assets/${product.picture}`)"
            />
          </td>
          <td>{{ product.name }}</td>
          <td>{{ product.size }}</td>
          <td>{{ product.price }}</td>
          <td>
            <btn-styled
              class="btnEdit"
              @click="
                editProduct(
                  product.id,
                  product.name,
                  product.price,
                  product.product_category_id,
                  product.size,
                  index,
                  product.picture
                )
              "
              >Edit</btn-styled
            >
          </td>
          <td>
            <btn-styled
              class="btnDelete"
              @click="removeProduct(product.id, product.picture, index)"
              >Remove</btn-styled
            >
          </td>
        </tr>
      </tbody>
    </table>
  </section>
  <h2 id="editProduct">Edit product</h2>
  <div class="edit">
    <form class="form" @submit.prevent="submitForm">
      <label for="edit_id">ID:</label><br />
      <input required type="number" name="edit_id" v-model="editID" /><br />
      <label for="edit_name">Name:</label><br />
      <input required type="text" name="edit_name" v-model="editName" /><br />
      <label for="product_size">Size:</label><br />
      <select
        required
        class="selectForm"
        name="product_size"
        v-model="editSize"
      >
        <option disabled value="">Select size...</option>
        <option value="Large">Large</option>
        <option value="Medium">Medium</option>
        <option value="Small">Small</option></select
      ><br />
      <label for="edit_price">Price:</label><br />
      <input required type="number" name="edit_price" v-model="editPrice" />
      <br />
      <label for="category">Category:</label><br />
      <select
        required
        class="selectForm"
        ref="inputCategory"
        name="category"
        v-model="editCategoryID"
      >
        <option disabled value="">Select a category...</option>
        <option
          v-for="category in categories"
          :key="category.id"
          :value="category.id"
        >
          {{ category.name }}
        </option></select
      ><br />
      <label for="image">Image:</label><br />
      <input type="file" name="image" @change="handleFileUpload($event)" />
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>
<script>
import axios from "axios";
import BtnStyled from "../components/BtnStyled.vue";
export default {
  components: { BtnStyled },
  data() {
    return {
      products: [],
      categories: [],
      editID: "",
      editName: "",
      editSize: "",
      editPrice: "",
      editCategoryID: "",
      editImage: null,
      saveIndex: "",
      oldImage: "",
    };
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
      "Bearer " + this.accessToken;
    const url = "http://localhost:3000/api/products/";
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.products = response.data;
      });
    const url2 = "http://localhost:3000/api/productCategory/";
    axios
      .get(url2, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.categories = response.data;
      });
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    removeProduct(id, productImg, index) {
      axios
        .post(
          "http://localhost:3000/api/products/removeproduct/" +
            id +
            "/" +
            productImg,
          {
            headers: {
              Authorization: "Bearer " + this.accessToken,
            },
          }
        )
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.products.splice(index, 1);
          alert("Product removed!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
    editProduct(id, name, price, categoryID, size, index, oldImg) {
      this.editName = name;
      this.editID = id;
      this.editCategoryID = categoryID;
      this.editSize = size;
      this.editPrice = price;
      this.saveIndex = index;
      this.oldImage = oldImg;
      document.getElementById("editProduct").scrollIntoView();
    },
    handleFileUpload(event) {
      this.editImage = event.target.files[0];
    },
    submitForm() {
      if (this.editImage != null) {
        const fd = new FormData();
        fd.append("name", this.editName);
        fd.append("size", this.editSize);
        fd.append("price", this.editPrice);
        fd.append("product_category_id", this.editCategoryID);
        fd.append("productImage", this.editImage);
        fd.append("id", this.editID);
        axios
          .post(
            "http://localhost:3000/api/products/editproductimg/" +
              this.oldImage,
            fd,
            {
              headers: {
                Authorization: "Bearer " + this.accessToken,
              },
            }
          )
          .then((res) => {
            alert("Product edited successfully!");
            //Perform Success Action
            console.log(res.data);
            this.$store.commit("increment");
          })
          .catch((error) => {
            // error.response.status Check status code
            console.log(error.response.status);
          });
      } else {
        axios
          .post(
            "http://localhost:3000/api/products/editproduct",
            {
              name: this.editName,
              id: this.editID,
              size: this.editSize,
              price: this.editPrice,
              product_category_id: this.editCategoryID,
            },
            {
              headers: {
                Authorization: "Bearer " + this.accessToken,
              },
            }
          )
          .then((res) => {
            //Perform Success Action
            console.log(res.data);
            this.$store.commit("increment");
            alert("Product edited successfully!");
          })
          .catch((error) => {
            // error.response.status Check status code
            console.log(error.response.status);
          });
      }
    },
  },
};
</script>

<style scoped>
.productsTable {
  border: 1px solid #999;
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 50%;
  width: 45%;
  position: relative;
  top: 15%;
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
}
img {
  width: 55px;
}
#editProduct {
  position: absolute;
  top: 54%;
  width: 30%;
  left: 55%;
}
.edit {
  position: absolute;
  top: 60%;
  left: 55%;
  font-family: "Bookerly";
  color: black;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  border-color: #a80000;
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
