# Backend Engineer Interview Project

### **High level spec**

Your task is to build a REST API in Node.js that returns data from sqlite database.

The `database.sqli` file is a database that includes 3 tables:
- *users* - each row represnts a single user
- *albums* - albums of a user
- *images* - images of an album

The `entities_sample.txt` file includes the structure of the tables and sample data of each table.

The API should have the following endpoints:
- Get User by user Id
- Get User and Albums by user Id
- Get Albums and Images by user Id
    - Endpoint should include support for pagination
    - Endpoint should include support for sorting by album title

-----

### **How to share your results?**
- [ ] Clone this repository and create your own branch to work on.
- [ ] .... develop .....
- [ ] Once you are ready, create a pull request with your code.


### **Evaluation:**
- [ ] There should be **at least** one test written and the README file should include instructions on how to execute it.
- [ ] You should provide clear documentation of the API, you can use Swagger or any other format.
- [ ] The app should build without errors (typically using `npm run build`). If there are necessary steps required to get it to compile, those should be covered in the README.md.
- [ ] No crashes or bugs.
- [ ] Code is easily understood and communicative (eg. comments, variable names, etc). 
- [ ] Everything that you decide to not do due to the limitation of time should be documented in the README.
- [ ] GitHub commit history is consistent, easy to follow and understand.

-----

### **Notes About Ergin's Additions:**

Sample application for requirements indicated above written in Java with Spring Boot framework.  It implements basic spring boot functionality and a Swagger UI for API references.

### Build and Deploy
- To build: `./mvn clean install`
- To run: `./mvn spring-boot:run`
- Documentation can be found from `http://localhost:8080/swagger-ui.html`

For development and testing purposes, application has a build in H2 in-memory DB for persistency with scripts to populate DB with test data.

###Improvements

- Domain Driven Design makes separation of API, business logic and infrastructure code (such as configurations etc.) easier. Since there isn’t any business logic I didn’t make a separation, so application service directly communicates with repositories. Adding a business / domain service layer may be considered.
- In multiple different cases same error is returned to keep the API simple and focus on the tasks. For example if a user doesn’t exist or a user exists without any albums or images throw same error to API end user. This may be improved. 
