<template>
  <div class="orders">
    <orders></orders>
  </div>
</template>

<script>
import Orders from "../components/Orders.vue";

export default {
  name: "OrderPage",
  components: {
    Orders,
  },
  data() {
    return {};
  },
};
</script>
<style scoped>
h1 {
  font-family: "Times New Roman", Times, serif;
  color: black;
}
</style>
