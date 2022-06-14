<script>
export default {
  beforeMount() {
    this.$store.commit("logout");
    this.$router.push("/");
  },
};
</script>
