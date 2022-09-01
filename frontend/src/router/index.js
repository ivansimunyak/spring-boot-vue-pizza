import {createRouter, createWebHashHistory} from "vue-router";
import OrderPage from "../views/OrderPage.vue";
import CheckDetails from "../views/CheckDetails.vue";
import EditOneOrder from "../views/EditOneOrder.vue";
import CategoriesPage from "../views/CategoriesPage.vue";
import UserTypePage from "../views/UserTypePage.vue";
import ProductsPage from "../views/ProductsPage.vue";
import Home from "../views/HomePage.vue";
import LoginPage from "../views/LoginPage.vue";
import RegisterPage from "../views/RegisterPage.vue";
import ProfilePage from "../views/ProfilePage.vue";
import ProfileOrderDetails from "../components/ProfileOrderDetails.vue";
import Logout from "../components/Logout.vue";
import Cart from "../components/Cart.vue";
import CheckoutPage from "../views/CheckoutPage.vue";
import AdminPage from "../views/AdminPage.vue";
import ReviewsPage from "@/views/ReviewsPage";

const routes = [
  {
    path: "/orders",
    name: "Order Page",
    component: OrderPage,
  },
  {
    path: "/products",
    name: "Products Page",
    component: ProductsPage,
  },
  {
    path: "/checkdetails/:id",
    name: "Check Details",
    component: CheckDetails,
  },
  {
    path: "/about",
    name: "About",
    component: () => import("../views/About.vue"),
  },
  {
    path: "/editorder/:id",
    name: "Edit Order",
    component: EditOneOrder,
  },
  {
    path: "/categories",
    name: "Categories Page",
    component: CategoriesPage,
  },
  {
    path: "/usertypes",
    name: "User Types",
    component: UserTypePage,
  },
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginPage,
  },
  {
    path: "/register",
    name: "Register",
    component: RegisterPage,
  },
  {
    path: "/profile",
    name: "Profile",
    component: ProfilePage,
  },
  {
    path: "/profileorder/:id",
    name: "Profile Order Details",
    component: ProfileOrderDetails,
  },
  {
    path: "/logout",
    name: "Logout",
    component: Logout,
  },
  {
    path: "/cart",
    name: "Cart",
    component: Cart,
  },
  {
    path: "/checkout",
    name: "Checkout",
    component: CheckoutPage,
  },
  {
    path: "/admin",
    name: "Admin Page",
    component: AdminPage,
  },
  {
    path: "/reviews",
    name: "Reviews Page",
    component: ReviewsPage,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
