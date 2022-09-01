<template>
  <edit-order
    :orderID="this.$route.params.id"
    :orderStatus="this.$route.params.orderStatus"
    :userPhone="this.$route.params.phone"
    :userAdress="this.$route.params.adress"
    :userName="this.$route.params.name"
  ></edit-order>
</template>
<script>
import EditOrder from "../components/EditOrder.vue";
export default {
  name: "Edit Order",
  components: {
    EditOrder,
  },
};
</script>
