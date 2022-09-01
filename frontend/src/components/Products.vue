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
            <img id="product-image" :src="product.imageUrl"/>
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
                  product.productCategory.id,
                  product.size,
                  index,
                  product.imageUrl
                )
              "
              >Edit</btn-styled
            >
          </td>
          <td>
            <btn-styled
                class="btnDelete"
                @click="removeProduct(product.id, product.imageUrl, index)"
            >Remove
            </btn-styled>
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
        </option>
      </select
      ><br/>
      <label for="image">Image:</label><br/>
      <input type="file" @change="handleUpload($event)"/>
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>
<script>
import axios from "axios";
import BtnStyled from "../components/BtnStyled.vue";
import {deleteObject, getStorage, ref} from "firebase/storage";
import "firebase/storage";
import {storage} from "../main";

export default {
  components: {BtnStyled},
  data() {
    return {
      products: [],
      categories: [],
      editID: "",
      editName: "",
      editSize: "",
      editPrice: "",
      editCategoryID: "",
      editUrl: "",
      file: null,
      imageData: null,
      saveIndex: "",
    };
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
      "Bearer " + this.accessToken;
    const url = "http://localhost:8080/api/product";
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.products = response.data;
      });
    const url2 = "http://localhost:8080/api/productcategory";
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
      const storage = getStorage();
      // Create a reference to the file to delete
      const desertRef = ref(storage, productImg);
      // Delete the file
      deleteObject(desertRef)
          .then(() => {
            // File deleted successfully
            axios
                .post(
                    "http://localhost:8080/api/product/deleteproduct",
                    {id: id},
                    {
                      headers: {
                        Authorization: "Bearer " + this.accessToken,
                      },
                    }
                )
                .then(() => {
                  //Perform Success Action
                  this.products.splice(index, 1);
                  alert("Product removed!");
                })
                .catch((error) => {
                  // error.response.status Check status code
                  console.log(error.response.status);
                });
          })
          .catch((error) => {
            console.log(error);
            // Uh-oh, an error occurred!
          });
    },
    handleUpload(event) {
      console.log(event.target.files[0]);
      const fileType = event.target.files[0].type;
      if (fileType === "image/jpeg" || fileType === "image/png") {
        this.file = event.target.files[0];
        this.error = null;
      } else {
        this.error = "Type not valid";
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
    editProduct(id, name, price, categoryID, size, index, imageUrl) {
      this.editName = name;
      this.editID = id;
      this.editCategoryID = categoryID;
      this.editSize = size;
      this.editPrice = price;
      this.saveIndex = index;
      this.editUrl = imageUrl;
      document.getElementById("editProduct").scrollIntoView();
    },
    async submitForm() {
      if (this.file != null) {
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
              (element) => element.id === this.editCategoryID
          );
          axios
              .post(
                  "http://localhost:8080/api/product/updateproduct",
                  {
                    id: this.editID,
                    name: this.editName,
                    size: this.editSize,
                    price: this.editPrice,
                    productCategory: {id: this.editCategoryID, name: record.name},
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
                const storage = getStorage();
                // Create a reference to the file to delete
                const desertRef = ref(storage, this.editUrl);
                // Delete the file
                deleteObject(desertRef)
                    .then(() => {
                      // File deleted successfully
                      this.uniqueProductKey++;
                      this.$store.commit("increment");
                      alert("Product edited!");
                    })
                    .catch((error) => {
                      console.log(error);
                      // Uh-oh, an error occurred!
                    });
              })
              .catch((error) => {
                // error.response.status Check status code
                console.log(error.response.status);
              });
        } catch (error) {
          console.log(error);
        }
      } else {
        axios
            .post(
                "http://localhost:8080/api/product/updateproduct",
                {
                  name: this.editName,
                  id: this.editID,
                  size: this.editSize,
                  price: this.editPrice,
                  productCategory: {id: this.editCategoryID},
                  imageUrl: this.editUrl,
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
  border-width: 1px;
  margin-bottom: 1%;
}
select {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
  margin-bottom: 1%;
}
</style>
