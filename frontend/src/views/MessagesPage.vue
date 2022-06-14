<template>
  <div class="messages">
    <messages></messages>
  </div>
</template>

<script>
import Messages from "../components/Messages.vue";

export default {
  name: "MessagesPage",
  components: {
    Messages,
  },
  data() {
    return {
      msgRemoved: this.$route.params.removeSuccess,
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    update() {
      this.$forceUpdate;
    },
  },
};
</script>
<style scoped>
h1 {
  font-family: "Times New Roman", Times, serif;
  color: black;
}
</style>
