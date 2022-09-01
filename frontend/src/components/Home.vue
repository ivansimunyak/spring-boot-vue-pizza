<template>
  <div v-if="showTitle" class="title-header">
    <h1>Welcome to our pizzeria</h1>
    <h2>Home to your favorite Italian pizza!</h2>
  </div>
  <btn-styled v-if="!orderProduct" id="orderHereBtn" @click="choiceMade"
    >Press here to order</btn-styled
  >
  <ul v-if="selectCategory" class="category-ul">
    <li
      v-for="(category, index) in categories"
      :key="index"
      class="category-li"
    >
      <btn-styled
        @click="chooseProductCategory(category.name)"
        class="category-btns"
        >{{ category.name }}</btn-styled
      >
    </li>
  </ul>
  <btn-styled
    v-if="productCategorySelected"
    @click="backToChoice"
    id="back-category"
    >Back to categories</btn-styled
  >
  <div @click="closeDialog" v-if="addedProduct"></div>
  <dialog v-if="addedProduct" open>
    <header>
      <slot name="header">
        <h2>Added to cart</h2>
      </slot>
    </header>
    <section>
      Sucessfully added to cart!<br /><br />
      <btn-styled @click="closeDialog">Okay</btn-styled>
    </section>
  </dialog>
  <section v-if="productCategorySelected" class="productsTable">
    <table>
      <thead>
        <tr>
          <th>Image</th>
          <th>Name</th>
          <th>Size</th>
          <th>Category</th>
          <th>Price</th>
          <th>Add to cart</th>
        </tr>
      </thead>
      <tbody>
      <tr v-for="(product, index) in products" :key="index">
        <td v-if="product.productCategory.name === selectedCategory">
          <img id="product-image" :src="product.imageUrl"/>
        </td>
        <td v-if="product.productCategory.name === selectedCategory">
          {{ product.name }}
        </td>
        <td v-if="product.productCategory.name === selectedCategory">
          {{ product.size }}
        </td>
        <td v-if="product.productCategory.name === selectedCategory">
          {{ product.productCategory.name }}
        </td>
        <td v-if="product.productCategory.name === selectedCategory">
          {{ product.price }}
        </td>
        <td v-if="product.productCategory.name === selectedCategory">
          <btn-styled class="btnDelete" @click="addToCart(product)"
          >Add to cart
          </btn-styled>
        </td>
      </tr>
      </tbody>
    </table>
  </section>
</template>
<script>
import axios from "axios";
import {mapMutations} from "vuex";
import BtnStyled from "./BtnStyled.vue";

export default {
  components: { BtnStyled },
  data() {
    return {
      products: [],
      categories: [],
      addedProduct: false,
      orderProduct: false,
      selectCategory: false,
      showTitle: true,
      productCategorySelected: false,
      selectedCategory: "",
    };
  },
  mounted() {
    const url = "http://localhost:8080/api/product";
    axios.get(url).then((response) => {
      this.products = response.data;
    });
    const url1 = "http://localhost:8080/api/productcategory";
    axios.get(url1).then((response) => {
      this.categories = response.data;
    });
  },
  methods: {
    ...mapMutations(["addProduct"]),
    chooseProductCategory(categoryName) {
      this.selectedCategory = categoryName;
      this.selectCategory = false;
      this.productCategorySelected = true;
    },
    addToCart(product) {
      this.addProduct(product);
      this.addedProduct = true;
    },
    closeDialog() {
      this.addedProduct = false;
    },
    choiceMade() {
      this.showTitle = false;
      this.orderProduct = true;
      this.selectCategory = true;
    },
    backToChoice() {
      this.productCategorySelected = false;
      this.selectCategory = true;
    },
  },
};
</script>
<style scoped>
div {
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100%;
  /* background-color: rgba(0, 0, 0, 0.75); */
  z-index: 10;
}
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

header {
  background-color: #a80000;
  color: white;
  width: 100%;
  padding: 1rem;
}

header h2 {
  margin: 0;
}

section {
  padding: 1rem;
}

menu {
  padding: 1rem;
  display: flex;
  justify-content: flex-end;
  margin: 0;
}

@media (min-width: 768px) {
  dialog {
    height: 10rem;
    width: 15rem;
  }
}
.productsTable {
  border-collapse: collapse;
  table-layout: fixed;
  color: #333;
  margin: 25px 0;
  background: white;
  font-size: 0.9em;
  padding: 0;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  text-align: center;
  margin-left: auto;
  margin-right: auto;
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
}
img {
  width: 125px;
}
#orderHereBtn {
  position: absolute;
  left: 35%;
  top: 45%;
  width: 30%;
  height: 5em;
  font-size: 16px;
}
.title-header {
  -webkit-text-stroke-width: 0.75px;
  -webkit-text-stroke-color: white;
  position: relative;
  height: 20%;
}
.category-ul {
  position: absolute;
  float: left;
  top: 30%;
  left: 37.5%;
  width: 30%;
  height: 5em;
  font-size: 16px;
  line-height: 180%;
  list-style: none;
}
.category-li {
  display: block;
  float: left;
  width: 350px; /* adjust */
  height: 50px; /* adjust */
  padding: 5px; /*adjust*/
}
.category-btns {
  width: 100%;
}
#back-category {
  position: relative;
  margin-right: 70%;
}
</style>
