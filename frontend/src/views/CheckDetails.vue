<template>
  <check-order-details :orderID="this.$route.params.id"></check-order-details>
</template>
<script>
import CheckOrderDetails from "../components/CheckOrderDetails.vue";
export default {
  name: "Check Details",
  components: {
    CheckOrderDetails,
  },
};
</script>
