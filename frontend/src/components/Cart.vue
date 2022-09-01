<template>
  <section class="productsTable">
    <table>
      <thead>
        <tr class="total">
          <th>Total: {{ total }}$</th>
          <th>
            <router-link class="nav-link" to="/checkout"
              >Checkout <font-awesome-icon :icon="['fas', 'arrow-right']"
            /></router-link>
          </th>
        </tr>

        <tr>
          <th>Image</th>
          <th>Name</th>
          <th>Size</th>
          <th>Category</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Subtotal</th>
          <th>Remove from cart</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(product, index) in products" :key="index">
          <td>
            <img id="product-image" :src="product.imageUrl"/>
          </td>
          <td>{{ product.name }}</td>
          <td>{{ product.size }}</td>
          <td>{{ product.productCategory.name }}</td>
          <td>{{ product.price }}</td>
          <td>
            <button class="quantity-btn" @click="decreaseQuantity(product)">
              <font-awesome-icon
                class="quantity-icon"
                :icon="['fas', 'circle-minus']"
              />
            </button>
            {{ product.quantity }}
            <button
              class="quantity-btn"
              @click="increaseProductQuantity(product)"
            >
              <font-awesome-icon
                class="quantity-icon"
                :icon="['fas', 'circle-plus']"
              />
            </button>
          </td>
          <td>{{ product.price * product.quantity }}</td>
          <td>
            <btn-styled class="btnDelete" @click="removeProduct(product)"
              >Remove from cart</btn-styled
            >
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
<script>
import {mapMutations} from "vuex";

export default {
  methods: {
    ...mapMutations(["removeCartItem", "decreaseQuantity", "increaseQuantity"]),
    removeProduct(product) {
      this.removeCartItem(product);
    },
    decreaseProductQuantity(product) {
      this.decreaseQuantity(product);
    },
    increaseProductQuantity(product) {
      this.increaseQuantity(product);
    },
  },
  computed: {
    products() {
      return this.$store.getters.cart;
    },
    total() {
      return this.$store.getters.cartTotal;
    },
  },
};
</script>

<style scoped>
.quantity-btn {
  border: none;
  background: none;
  cursor: pointer;
  margin: 0;
  padding: 0;
}
.quantity-icon {
  color: #a80000;
}
thead tr {
  background-color: #0a0000;
}
.productsTable {
  border-collapse: collapse;
  table-layout: fixed;
  color: #333;
  margin: 25px 0;

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
  background: white;
}
img {
  width: 125px;
}
</style>
