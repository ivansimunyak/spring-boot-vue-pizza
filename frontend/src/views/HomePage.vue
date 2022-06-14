<template>
  <home />
</template>
<script>
import { mapGetters } from "vuex";
import Home from "../components/Home.vue";
export default {
  components: {
    Home,
  },
  computed: {
    ...mapGetters(["user", "isAdmin", "isEmployee"]),
  },
};
</script>
