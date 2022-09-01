<template>
  <div id="wrapper">
    <h1>Admin</h1>
    <admin />
  </div>
</template>
<script>
import Admin from "../components/Admin.vue";

export default {
  components: { Admin },
};
</script>
<style scoped>
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 80%;
  height: 175%;
  margin: 0 10% 10%;
  top: 10%;
  border-style: outset;
  border-color: #a80000;
}
</style>
