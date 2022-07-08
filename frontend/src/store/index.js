import {createStore} from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    }),
  ],
  state: {
    uniqueProductKey: 0,
    username: null,
    accessToken: null,
    isAdmin: null,
    isEmployee: null,
    cart: [],
    cartCount: 0,
  },
  mutations: {
    increment(state) {
      state.uniqueProductKey++;
    },
    resetCart(state) {
      (state.cartCount = 0), (state.cart = []);
    },
    setUsername(state, username) {
      state.username = username;
    },
    setToken(state, accessToken) {
      state.accessToken = accessToken;
    },
    setAdmin(state, isAdmin) {
      state.isAdmin = isAdmin;
    },
    setEmployee(state, isEmployee) {
      state.isEmployee = isEmployee;
    },
    logout(state) {
      (state.username = null),
        (state.isAdmin = null),
        (state.isEmployee = null),
        (state.accessToken = null),
        (state.cartCount = 0),
        (state.cart = []);
    },
    addProduct: (state, product) => {
      const record = state.cart.find((element) => element.id === product.id);
      if (record) {
        record.quantity++;
      } else {
        product.quantity = 1;
        state.cartCount++;
        state.cart.push(product);
      }
    },
    removeCartItem(state, item) {
      const record = state.cart.find((element) => element.id === item.id);
      state.cart.splice(state.cart.indexOf(record), 1);
    },
    decreaseQuantity(state, item) {
      const record = state.cart.find((element) => element.id == item.id);
      record.quantity--;
      if (record.quantity === 0) {
        state.cartCount--;
        state.cart.splice(state.cart.indexOf(record), 1);
      }
    },
    increaseQuantity(state, item) {
      const record = state.cart.find((element) => element.id === item.id);
      record.quantity++;
    },
  },
  actions: {},
  modules: {},
  getters: {
    isLoggedIn(state) {
      return !!state.accessToken;
    },
    username(state) {
      return state.username;
    },
    isAdmin(state) {
      return state.isAdmin;
    },
    isEmployee(state) {
      return state.isEmployee;
    },
    accessToken(state) {
      return state.accessToken;
    },
    cart(state) {
      return state.cart;
    },
    cartTotal(state) {
      let res = 0;
      state.cart.map((item) => {
        res += item.price * item.quantity;
      });
      return res;
    },
    cartCount(state) {
      return state.cartCount;
    },
  },
});
