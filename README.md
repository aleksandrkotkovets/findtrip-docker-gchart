# findtrip


The application is called "FindTrip" and is an analog of aviasales, but with simpler functionality.
branch master.


1. 
In the application.yml file:

      change #JPA PROPERTIES from "ddl-auto: none" to "ddl-auto: create-drop";
      cnange #DATABASE parameters;
In INTELLIJE IDEA File->Setting in File Encoding change the encoding to UTF-8;

2. After starting the project, run the data.script to insert data into the table
  
3. Sign in

Admin:

     login: admin
   
     password: adminadmin
   
Worker:

    login: worker
   
    password: workerworker
   
Client:

    login: client
   
    password: clientclient
   
Docker:
    mvn package
    docker-compose up --build
    docker-compose down
    
PostgreSQL docker container:
    docker exec -it <container_id> psql -U postgres findtrip_db
    \dt
    