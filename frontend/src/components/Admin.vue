<template>
  <div id="find-user">
    <h2>Find User</h2>
    <input v-model="searchingUser" type="text" />
    <br />
    <btn-styled @click="searchUser = true">Search</btn-styled>
    <btn-styled @click="searchUser = false">Reset</btn-styled>
  </div>
  <section class="usersSearchTable">
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Email</th>
          <th>Phone Number</th>
          <th>User Type</th>
          <th>Set admin</th>
          <th>Set employee</th>
          <th>Set customer</th>
          <th>Delete user</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="index">
          <td v-if="user.username === searchingUser && searchUser">
            {{ user.id }}
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            {{ user.username }}
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            {{ user.email }}
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            {{ user.phoneNumber }}
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            {{ user.userType.name }}
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            <btn-styled class="btnEdit" @click="setType('Admin', user, index)"
              >Set Admin</btn-styled
            >
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            <btn-styled
              class="btnDelete"
              @click="setType('Employee', user, index)"
              >Set Employee</btn-styled
            >
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            <btn-styled
              class="btnEdit"
              @click="setType('Customer', user, index)"
              >Set Customer</btn-styled
            >
          </td>
          <td v-if="user.username === searchingUser && searchUser">
            <btn-styled
              class="btnDelete"
              @click="removeUser(user.user_id, index)"
              >Remove</btn-styled
            >
          </td>
        </tr>
      </tbody>
    </table>
  </section>
  <h2 id="table-header">List of all users</h2>
  <section class="usersTable">
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Email</th>
          <th>Phone Number</th>
          <th>User Type</th>
          <th>Set admin</th>
          <th>Set employee</th>
          <th>Set customer</th>
          <th>Delete user</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="index">
          <td>
            {{ user.id }}
          </td>
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.phoneNumber }}</td>
          <td>{{ user.userType.name }}</td>
          <td>
            <btn-styled class="btnEdit" @click="setType('Admin', user, index)"
              >Set Admin</btn-styled
            >
          </td>
          <td>
            <btn-styled
              class="btnDelete"
              @click="setType('Employee', user, index)"
              >Set Employee</btn-styled
            >
          </td>
          <td>
            <btn-styled
              class="btnEdit"
              @click="setType('Customer', user, index)"
              >Set Customer</btn-styled
            >
          </td>
          <td>
            <btn-styled class="btnDelete" @click="removeUser(user.id, index)"
            >Remove
            </btn-styled>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      users: [],
      searchingUser: "",
      searchUser: false,
      userTypes: [],
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
    const url = "http://localhost:8080/api/user/";
    axios
      .get(url, {
        headers: {
          Authorization: "Bearer " + this.accessToken,
        },
      })
      .then((response) => {
        this.users = response.data;
      })
      .catch((error) => {
        // error.response.status Check status code
        console.log(error.response.status);
      });
    const url1 = "http://localhost:8080/api/usertype/";
    axios.get(url1).then((response) => {
      this.userTypes = response.data;
    });
  },
  methods: {
    setType(user_type_name, user, index) {
      const record = this.userTypes.find(
        (element) => element.name === user_type_name
      );
      axios
        .post(
            "http://localhost:8080/api/user/updateusertype",
            {id: user.id, userType: {id: record.id, name: record.name}},
            {
              headers: {
                Authorization: "Bearer " + this.accessToken,
              },
            }
        )
        .then(() => {
          this.users.splice(index, 1, {
            id: user.id,
            userType: {id: record.id, name: record.name},
            username: user.username,
            email: user.email,
            phoneNumber: user.phoneNumber,
          });
        })
        .catch((error) => {
          console.log(error.response.status);
        });
    },
    removeUser(id, index) {
      axios
        .post("http://localhost:8080/api/user/deleteuser", { id: id })
        .then((res) => {
          console.log(res.data);
          this.users.splice(index, 1);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
#table-header {
  position: absolute;
  margin-left: 40%;
  margin-top: 10%;
}
#find-user {
  position: relative;
  margin-top: 5%;
  margin-left: 35%;
  border-style: groove;
  width: 30%;
  background-color: rgb(255, 255, 255);
  border-color: #a80000;
}
.usersTable {
  margin-top: 10%;
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 30%;
  width: 95%;
  position: relative;
  top: 10%;
  margin-left: 2.5%;
  border-color: #a80000;
}
.usersSearchTable {
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  width: 95%;
  position: relative;
  top: 10%;
  margin-left: 2.5%;
  border-color: #a80000;
  margin-bottom: 5%;
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
input {
  padding: 5px;
  margin: 5px 0;
  border-radius: 10px;
  border-width: 1px;
  margin-bottom: 1%;
}
</style>
