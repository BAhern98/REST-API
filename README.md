# REST-API
This project is a RESTful API developed using spring framework that also utilises other third party APIs to add additional functionality. 

This REST API was made using 

* Spring MVC
* MAVEN to manage all dependencies
* Java Persistence API(JPA) to create Entity and Named Queries
* Tomcat Server
* Has universal error handling using @ControllerAdvice annotation


Functionality of this project include

* All methods support the GET, POST, PUT and DELETE HTTP methods. 

* All responses from these HTTP methods are in JSON format.

* GET requests adhere to HATEOAS principles.

* Some methods use third party APIs, an example of a third party API I used was for the weather, Weather details based on the latitude and longitude in the database.

* This REST API returns a QR code for a specific brewery that can be scanned by your phone and will open up a google map with the specified brewery on a google map with the latitude and longitude coordinates of the brewery plotted on it and the name of the specified brewery.
