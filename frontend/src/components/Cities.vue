<template>
  <section class="citiesTable">
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Edit</th>
          <th>Remove</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(city, index) in cities" :key="index">
          <td>{{ city.name }}</td>
          <td>
            <btn-styled
              class="btnEdit"
              @click="editCity(city.id, city.name, index)"
              >Edit</btn-styled
            >
          </td>
          <td>
            <btn-styled class="btnDelete" @click="removeCity(city.id, index)"
              >Remove</btn-styled
            >
          </td>
        </tr>
      </tbody>
    </table>
  </section>
  <h2 id="editCity">Edit city</h2>
  <div class="edit">
    <form class="form" @submit.prevent="editCityForm">
      <label for="id">ID:</label><br />
      <input required type="number" name="editID" v-model="editID" /><br />
      <label for="editName">Name:</label><br />
      <input
        required
        type="text"
        name="editName"
        v-model="editCityName"
      /><br />
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      cities: [],
      editCityName: "",
      editID: "",
    };
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
      "Bearer " + this.accessToken;
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
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  methods: {
    editCity(id, name, index) {
      this.editCityName = name;
      this.editID = id;
      this.saveIndex = index;
    },
    editCityForm() {
      axios
        .post(
          "http://localhost:3000/api/city/editCity",
          { name: this.editCityName, id: this.editID },
          {
            headers: {
              Authorization: "Bearer " + this.accessToken,
            },
          }
        )
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.cities.splice(this.saveIndex, 1, {
            name: this.editCityName,
            id: this.editID,
          });
          alert("City edited successfully!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
    removeCity(id, index) {
      axios
        .post("http://localhost:3000/api/city/removecity/" + id, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((res) => {
          //Perform Success Action
          console.log(res.data);
          this.cities.splice(index, 1);
          alert("City removed!");
        })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
        });
    },
  },
};
</script>
<style scoped>
.citiesTable {
  border: 1px solid #999;
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 250px;
  width: 40%;
  position: relative;
  top: 30%;
  margin-left: 2.5%;
  border-color: #a80000;
}
table {
  border-collapse: collapse;
  width: 100%;
}
th {
  position: sticky;
  top: 0;
  background: #a80000;
  padding: 10px 5px;
  text-align: center;
  border-bottom: 1px solid #a80000;
  color: white;
  z-index: 3;
}
td {
  padding: 5px 5px;
  text-align: center;
  z-index: 1;
}
#editCity {
  position: absolute;
  top: 55%;
  width: 30%;
  margin-left: 25%;
  left: 25%;
}
.edit {
  position: absolute;
  top: 66%;
  left: 50%;
  font-family: "Bookerly";
  color: black;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  border-color: #a80000;
}
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  box-shadow: 5px;
  border-width: 1px;
}
</style>
