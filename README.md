# AngularJS (frontend) + Spring Boot (backend) sample application
## Sample project that shows the division of a application in frontend and backend.
#### The main objective is to demonstrate how an application can be divided into loosely couple components. The fronend and backend separation is extremelly important as we normally have different developers working on each layer with different technologies.
#### This separation allow for both components to evolve independently in its own pace (as long as we keep the REST API interfaces). Each change can be easily tested without the need to deploy the hole application.
#### For this example we used CRL (Certificate Revogation List) as the target domain. The implemented use cases are:
    - Insert a new CRL with: Authorit Key (String) and the CRL File (CRL File).
    - Retrieve all CRLs.
    - Get the CRL file for a particular CRL.
 
