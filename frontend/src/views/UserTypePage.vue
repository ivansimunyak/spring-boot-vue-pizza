<template>
  <div id="wrapper">
    <h1 id="head">User Types</h1>
    <user-types :key="this.uniqueTypeKey" />
    <h2 id="addNew">Add new type</h2>
    <div class="addType">
      <form class="form" @submit.prevent="submitForm">
        <label>Name:</label><br />
        <input type="text" required v-model="addingName" /><br />
        <btn-styled type="submit">Submit</btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import BtnStyled from "../components/BtnStyled.vue";
import UserTypes from "../components/UserTypes.vue";

export default {
  components: { BtnStyled, UserTypes },
  data() {
    return {
      addingName: "",
      uniqueTypeKey: 0,
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    submitForm() {
      axios
          .post(
              "http://localhost:8080/api/usertype/addusertype",
              {name: this.addingName},
              {
                headers: {
                  Authorization: "Bearer " + this.accessToken,
                },
              }
          )
          .then(() => {
            this.uniqueTypeKey++;
            this.addingName = "";
            alert("User type added!");
          })
        .catch((error) => {
          console.log(error.response.status);
        });
    },
  },
};
</script>
<style scoped>
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 80%;
  height: 100%;
  top: 10%;
  margin: 0 10%;
  border-style: outset;
  border-color: #a80000;
}
#head {
  position: absolute;
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  width: 30%;
  left: 30%;
  top: 1%;
}
.addType {
  font-family: "Bookerly", Times, serif;
  color: black;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  position: absolute;
  left: 50%;
  top: 30%;
  border-color: #a80000;
}
#addNew {
  position: absolute;
  left: 55%;
  top: 19%;
}
input[type="text"] {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
}
</style>
