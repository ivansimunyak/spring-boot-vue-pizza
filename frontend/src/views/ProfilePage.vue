<template>
  <div id="wrapper">
    <base-dialog
      v-if="deleteUser"
      title="Delete Your Profile"
      @close="closeDialog"
    >
      <template #default>
        <!-- delete profile below -->
        <h1>{{ errorMessage }}</h1>
        <form @submit.prevent="submitDelete">
          <label id="enter-password" for="password"
            >Please enter your password</label
          ><br />
          <input
            type="password"
            name="password"
            placeholder="Password"
            v-model="password"
          /><br />
          <btn-styled type="submit">Submit</btn-styled>
        </form>
      </template>
    </base-dialog>
    <base-dialog v-if="editUser" title="Edit Your Profile" @close="closeDialog">
      <template #default>
        <h1>{{ errorMessage }}</h1>
        <div v-if="!choiceMade">
          <btn-styled @click="makeChoice('pw')">Change Password</btn-styled>
          <btn-styled @click="makeChoice('data')"
            >Change Information</btn-styled
          >
        </div>
        <btn-styled id="back-btn" v-if="choiceMade" @click="resetChoice"
          >Back</btn-styled
        >
        <!-- Below is edit form -->
        <form
            v-if="editData"
            class="RegistrationPageForm"
            @submit.prevent="submitEdit"
        >
          <b><label>First Name:</label></b
          ><br/>
          <input
              ref="first-name"
              id="name"
              type="text"
              maxlength="25"
              name="name"
              placeholder="Name"
              pattern="[A-z]+"
              :value="localUser.firstName"
              required
          /><br/>
          <b><label>Last Name:</label></b
          ><br/>
          <input
              ref="last-name"
              id="name"
              type="text"
              maxlength="25"
              name="name"
              placeholder="Name"
              pattern="[A-z]+"
              :value="localUser.lastName"
              required
          /><br/>
          <b><label>Email:</label></b
          ><br/>
          <input
              ref="email"
              id="email"
              type="email"
              maxlength="49"
              name="email"
              placeholder="Email"
              :value="localUser.email"
              required
          /><br/>
          <b><label>Adress: </label></b><br/>
          <input
              ref="adress"
              id="adress"
              type="text"
              name="adress"
              maxlength="25"
              placeholder="Adress"
              :value="localUser.adress"
              required
          /><br/>
          <b><label>Phone Number:(+381) </label></b><br/>
          <input
              ref="phone"
              id="phone"
              type="number"
              placeholder="Phone Number"
              name="phone"
              max="2147483645"
              min="0"
              :value="localUser.phoneNumber"
              required
          /><br/>
          <br/><br/>
          <btn-styled type="submit">Submit</btn-styled>
        </form>
        <!-- below is edit password -->
        <form v-if="editPassword" @submit.prevent="changePassword">
          <b><label for="old-pw">Old password:</label></b
          ><br />
          <input
            type="password"
            name="old-pw"
            placeholder="Old Password"
            v-model="oldPassword"
          /><br />
          <b><label for="new-pw">New password:</label></b
          ><br />
          <input
            type="password"
            name="new-pw"
            placeholder="New Password"
            v-model="newPassword"
          /><br />
          <btn-styled type="submit">Submit</btn-styled>
        </form>
      </template>
    </base-dialog>
    <!-- profile below -->
    <h1>Your Profile</h1>
    <div class="profile-list">
      <ul>
        <li>Username: {{ localUser.username }}</li>
        <br/>
        <li>First Name:{{ localUser.firstName }}</li>
        <br/>
        <li>Last Name:{{ localUser.lastName }}</li>
        <br/>
        <li>Email:{{ localUser.email }}</li>
        <br/>
        <li>Adress:{{ localUser.adress }}</li>
        <br/>
        <li>Phone Number: {{ localUser.phoneNumber }}</li>
        <br/>
      </ul>
    </div>
    <!-- below is table with orders -->
    <div v-if="profileOrders.length > 0" class="wrap-table">
      <table class="table">
        <thead>
          <tr>
            <th v-for="(header, index) in headers" :key="index">
              {{ header }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(profileOrder, index) in profileOrders" :key="index">
            <td v-for="(column, indexColumn) in columns" :key="indexColumn">
              {{ profileOrder[column] }}
            </td>
            <td>
              <btn-styled
                @click="
                  $router.push({ path: `/profileorder/${profileOrder.id}` })
                "
                >Check Details</btn-styled
              >
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <btn-styled id="edit-profile" @click="editProfile">Edit Profile</btn-styled>
    <btn-styled id="delete-profile" @click="deleteProfile"
      >Delete Profile</btn-styled
    >
  </div>
</template>
<script>
import {mapGetters} from "vuex";
import BtnStyled from "../components/BtnStyled.vue";
import axios from "axios";

export default {
  components: {
    BtnStyled,
  },
  data() {
    return {
      columns: ["id", "status", "adress", "phoneNumber"],
      headers: ["Order ID", "Order Status", "Adress", "Phone", "Check Details"],
      oldPassword: "",
      newPassword: "",
      profileOrders: [],
      localUser: [],
      editingUser: [],
      cities: [],
      deleteUser: false,
      editUser: false,
      editData: false,
      editPassword: false,
      choiceMade: false,
      password: "",
      localKey: 0,
      found: "",
      errorMessage: "",
    };
  },
  computed: {
    accessToken() {
      return this.$store.getters.accessToken;
    },
    user() {
      return this.$store.getters.username;
    },
    ...mapGetters(["username"]),
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
        "Bearer " + this.accessToken;

    const url1 =
        "http://localhost:8080/api/user/getuser/?username=" + this.username;
    axios
        .get(url1, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((response) => {
          this.localUser = response.data;
        });
    const url =
        "http://localhost:8080/api/orders/getcustomerorders/?username=" +
        this.username;
    axios
        .get(url, {
          headers: {
            Authorization: "Bearer " + this.accessToken,
          },
        })
        .then((response) => {
          this.profileOrders = response.data;
        });
  },
  methods: {
    deleteProfile() {
      this.deleteUser = true;
    },
    editProfile() {
      this.editUser = true;
      this.editingUser = this.localUser;
    },
    makeChoice(choice) {
      this.choiceMade = true;
      if (choice === "pw") {
        this.editPassword = true;
      } else this.editData = true;
    },
    resetChoice() {
      this.choiceMade = false;
      this.editPassword = false;
      this.editData = false;
    },
    submitDelete() {
      axios.defaults.headers.common["Authorization"] =
        "Bearer " + this.accessToken;
      axios
          .post("http://localhost:8080/api/user/deleteprofile", {
            id: this.localUser.id,
            password: this.password,
          })
          .then(() => {
            this.$router.push("/logout");
          })
        .catch((error) => {
          // error.response.status Check status code
          //  console.log( error.response.status)
          this.errorMessage = error;
        });
    },
    changePassword() {
      if (this.oldPassword === this.newPassword)
        return alert("Old password is the same as new password!");
      axios.defaults.headers.common["Authorization"] =
        "Bearer " + this.accessToken;
      axios
          .post("http://localhost:8080/api/user/changepassword", {
            id: this.localUser.id,
            password: this.newPassword,
          })
          .then(() => {
            //Perform Success Action
            this.closeDialog();
            alert("Password changed successfully! ");
          })
        .catch((error) => {
          // error.response.status Check status code
          console.log(error.response.status);
          console.log(error);
        });
    },
    submitEdit() {
      axios.defaults.headers.common["Authorization"] =
        "Bearer " + this.accessToken;
      axios
          .post("http://localhost:8080/api/user/updateprofile", {
            email: this.$refs.email.value,
            adress: this.$refs.adress.value,
            firstName: this.$refs["first-name"].value,
            lastName: this.$refs["last-name"].value,
            phoneNumber: this.$refs.phone.value,
            id: this.localUser.id,
          })
        .then((res) => {
          //Perform Success Action
          if (res.status === 200) {
            this.localUser.username = this.username;
            this.localUser.email = this.$refs.email.value;
            this.localUser.adress = this.$refs.adress.value;
            this.localUser.firstName = this.$refs["first-name"].value;
            this.localUser.lastName = this.$refs["last-name"].value;
            this.localUser.phoneNumber = this.$refs.phone.value;

            this.closeDialog();
          }
        })
        .catch((error) => {
          this.errorMessage = error;
        });
    },
    closeDialog() {
      this.deleteUser = false;
      this.editUser = false;
      this.choiceMade = false;
      this.editData = false;
      this.editPassword = false;
    },
  },
};
</script>

<style scoped>
*,
*:before,
*:after {
  box-sizing: border-box;
}
#wrapper {
  background-color: #ffffffd9;
  position: absolute;
  width: 80%;
  height: 120%;
  top: 10%;
  margin: 0px 10%;
  border-style: outset;
  border-color: #a80000;
}
.profile-list {
  top: 20%;
  left: 10%;
  position: absolute;
  background-color: white;
  width: 40%;
  border-style: ridge;
  border-color: #a80000;
  height: 30%;
  padding: 5px;
  margin: 5px 0;
  border-radius: 15px;
}
#back-btn {
  position: absolute;
  left: 1%;
  width: 15%;
}
.profile-list ul {
  margin-top: 1%;
  list-style-type: none;
  text-align: left;
  font-size: 25px;
}
li {
  border-bottom: 1px solid #a80000;
  margin-left: 15px;
}
.wrap-table {
  border-radius: 1px;
  color: #333;
  background: white;
  overflow: auto;
  height: 45%;
  width: 80%;
  position: absolute;
  top: 40%;
  margin: 10%;
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

#edit-profile {
  position: relative;
  width: 25%;
  top: 15%;
  left: 35%;
  height: 40px;
}
#delete-profile {
  position: relative;
  width: 25%;
  height: 40px;
  top: 25%;
  left: 9%;
}
form {
  width: 100%;
  text-align: center;
}
input {
  padding: 3px;
  margin: 3px 0;
  border-radius: 10px;
  border-width: 1px;
}
select {
  padding: 3px;
  margin: 3px 0;
  border-radius: 10px;
  border-width: 1px;
}
</style>
