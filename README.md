# Spring Boot, Vue.js Pizza Place


## Short Description

This project is a modified version of "Pizzeria", this project differs from the first in a few ways.
One of those is I deleted all unnecessary SQL tables(cities,locations,employee,etc.). This web app is designed for a
pizzeria
that has one store. It is pretty simple to use, if you want to order, just press the button on home page to order,
select a product category and order what you would like to eat.
Like the previous version, website has 4 different interfaces, and the principle remains unchanged.
You have guest interface, which is unregistered user, providing only basic functionality, which is to order products and
nothing else.
Next you have customer interface, customers are registered users, and they have apart from the ability to order, they
can check all their previous orders on their profile page,
registered users process of ordering is also simplified, in a way that on checkout page all the user information fields
come pre-filled with information they provided on register,
and that data is changable if the user so decides. Customers also have ability to change all of their data and password
on the profile page, if some of the data changes.
New addition to this version is the ability to leave reviews to the admin, the way they can do that is that they go to
their profile, go to the order details page, and if the order status is either "Delivered" or "Canceled"
they can leave their rating of the order ranging from "Excellent" to "Bad", with an ability to leave a comment providing
more details on the order.
Next is employee interface, this remains unchanged from the previous version, employees apart from all the functionality
regular registered customers have,
they can view all the orders and edit their information if there is some mistake, and also change the status of the
order.
Finally, we have admin interface, admin has all the rights to add, edit and remove data from the application. New
addition is reviews page where admin can check all the reviews that have been submitted, and the ability to remove them.
And those are the main features of this project, I will continue to add more features as time goes on. Currently, im
looking for ways to improve security on the backend, and I will add more functionality to reviews, and add some
statistics to this project
(e.g. how many users registered, how many orders, how many happy customers, etc.). So if you have any questions or
suggestions feel free to message me, and also checkout the previous version of this project on my GitHub.

## Technologies

Project is built with:

* Vue.js
* Spring Boot
* MySQL

## Project setup

Set up database from pizzaplace.sql and run commands below to start.
For the backend to work run PizzaPlaceApplication.java, you need to set your own credentials in application.properties

At login, you can use any of the following three combinations to see different interfaces:

1. username: admin password: admin
2. username: employee password: employee
3. username: customer password: customer

```
## Install project

npm install

## Frontend

npm run serve

```
