# boxinatorAPI

The API uses Maven as its build automation tool. Using the popular Java IDEs IntelliJ and Eclipse should give you access to Maven already, so running the project should not be too much of a hassle.

- Clone the repository

*DB*

- Take _boxinator_db_dump.sql_ from root directory 
- Run the sql file on your MySQL setup
  - You should now have the database with some dummy data in it 

*Backend Project*

- Open the project in IntelliJ or Eclipse
- Navigate and change src/main/resources/application.properties
  - Replace strings as necessary
    - spring.datasource.username => _Your MySQL username_
    - spring.datasourcepassword  => _Your MySQL password_
    - If your MySQL is running on a port other than 3306, change the 3306 number at the top to your MySQL port number
- From within your IDE, you can now run the project. The main method is in BoxinatorApiApplication. 
  - If something fails, try running 'mvn clean' and 'mvn install' and try starting the project again
- The API should now be running on localhost:5000
