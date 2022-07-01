<template>
  <section class="typesTable">
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Edit</th>
          <th>Remove</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(type, index) in types" :key="index">
          <td>{{ type.name }}</td>
          <td>
            <btn-styled
              class="btnEdit"
              @click="editType(type.id, type.name, index)"
              >Edit</btn-styled
            >
          </td>
          <td>
            <btn-styled class="btnDelete" @click="removeType(type.id, index)"
              >Remove</btn-styled
            >
          </td>
        </tr>
      </tbody>
    </table>
  </section>
  <h2 id="editType">Edit type</h2>
  <div class="edit">
    <form class="form" @submit.prevent="editTypeForm">
      <label for="id">ID:</label><br/>
      <input type="number" required name="editID" v-model="editID"/><br/>
      <label for="fname">Name:</label><br/>
      <input
          type="text"
          id="editID"
          required
          name="editName"
          v-model="editName"
      /><br/>
      <btn-styled type="submit">Submit</btn-styled>
    </form>
  </div>
</template>
<script>
import axios from "axios";
import BtnStyled from "../components/BtnStyled.vue";

export default {
  components: { BtnStyled },
  data() {
    return {
      types: [],
      editName: "",
      editID: "",
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
      "Bearer " + this.accessToken;
    const url = "http://localhost:8080/api/usertype";
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.types = response.data;
      });
  },
  methods: {
    editTypeForm() {
      axios
          .post(
              "http://localhost:8080/api/usertype/editusertype",
              {name: this.editName, id: this.editID},
              {
                headers: {
                  Authorization: "Bearer " + this.accessToken,
                },
              }
          )
          .then(() => {
            //Perform Success Action
            this.types.splice(this.saveIndex, 1, {
              name: this.editName,
              id: this.editID,
            });
            alert("Type edited successfully!");
          })
        .catch((error) => {
          console.log(error.response.status);
        });
    },
    editType(id, name, index) {
      this.editName = name;
      this.editID = id;
      this.saveIndex = index;
    },
    removeType(id, index) {
      axios
          .post(
              "http://localhost:8080/api/usertype/deleteusertype",
              {id: id},
              {
                headers: {
                  Authorization: "Bearer " + this.accessToken,
                },
              }
          )
          .then(() => {
            this.types.splice(index, 1);
            alert("Type removed!");
          })
        .catch((error) => {
          console.log(error.response.status);
        });
    },
  },
};
</script>
<style scoped>
.typesTable {
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
#editType {
  position: absolute;
  top: 45%;
  width: 30%;
  margin-left: 25%;
  left: 25%;
}
.edit {
  position: absolute;
  top: 55%;
  left: 50%;
  font-family: "Bookerly", Times, serif;
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
  border-width: 1px;
}
</style>
