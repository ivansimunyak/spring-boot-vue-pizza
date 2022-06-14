<template>
  <div id="wrapper">
    <locations :key="uniqueLocKey" />
    <h2 id="addNew">Add new location</h2>
    <div class="addLocation">
      <form class="form" @submit.prevent="submitForm">
        <label>Location name:</label><br />
        <input type="text" required v-model="addingName" /><br />
        <label>City:</label><br />
        <select required v-model="addingCityID">
          <option disabled value="">Choose a city...</option>
          <option v-for="city in cities" :key="city.id" :value="city.id">
            {{ city.name }}
          </option></select
        ><br /><br />
        <btn-styled type="submit">Submit</btn-styled>
      </form>
    </div>
  </div>
</template>
<script>
import BtnStyled from "../components/BtnStyled.vue";
import Locations from "../components/Locations.vue";
import axios from "axios";
export default {
  components: {
    Locations,
    BtnStyled,
  },
  data() {
    return {
      cities: [],
      addingName: "",
      addingCityID: "",
      uniqueLocKey: 0,
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
          "http://localhost:3000/api/location/addlocation",
          { name: this.addingName, city_id: this.addingCityID },
          {
            headers: {
              Authorization: "Bearer " + this.accessToken,
            },
          }
        )
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.uniqueLocKey++;
          this.addingName = "";
          alert("Location added!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
  },
  mounted() {
    const url = "http://localhost:3000/api/city/";
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.cities = response.data;
      });
  },
};
</script>
<style scoped>
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 80%;
  height: 110%;
  margin: 0px;
  top: 10%;
  margin-left: 10%;
  margin-right: 10%;
  border-style: outset;
  border-color: #a80000;
}
.addLocation {
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
  box-shadow: 5px;
  border-width: 1px;
}
</style>
