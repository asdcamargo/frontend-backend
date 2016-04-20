# Backend sample application

## This project was designed as an example to show how an application can be splitted into loosely coupled components
## This backend project is composed by:

* **Framework:** [Spring Boot](https://github.com/spring-projects/spring-boot.git) v1.2.4
* **Database:** This application uses in memory HSQLDB. To change to any relational database only the driver dependency 
and the connection parameters need to be added.

## How to run

$ git clone https://github.com/asdcamargo/frontend-backend.git

$ cd crl-data-backend

$ mvn clean install

$ java -jar target/crl-data-backend-0.0.1.jar

* The embedded tomcat will start to run on port 8080. To test it go to your browser and access: http://localhost:8080/health. The response should be: {"status":"UP"}

## How to use it

* If the frontend application is not running yet go to the frontend and follow the steps to get it up and running.
* Then you can perform the actions in the frontend app.

## Available REST Ooperations

* Save a CRL
* Get a CRL file by its id
* Get all CRLs
* Get CRL by id
* Delete CRL by id
