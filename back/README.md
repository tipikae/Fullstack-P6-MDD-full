# Monde de Dév MDD API
MDD API is the back-end of the MDD application, a social network for developer, powered by Spring Boot 3.2.1 framework.

## Prerequisites
- Java 17
- Maven 3.8.6
- MySQL 8.0.35

## Installation
First install [Java](https://www.oracle.com/fr/java/technologies/downloads/#java17) according your operating system.

Then install [Maven](https://maven.apache.org/install.html).

And finally install [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/).

Go to `resources/sql` in the root directory and :
* open a terminal,
* connect to mysql with your credentials,
* create a database `CREATE DATABASE mdd;`,
* import the SQL script `SOURCE script.sql;`.

Open `src/main/resources/application-prod.properties` and update `spring.datasource.username` and `spring.datasource.password` with your MySQL credentials.

## Run
In the root directory execute `mvn clean verify` in order to compile, test and package the archive.

There are two profiles to launch the application:
- `dev`: default profile, for development only, with in-memory database,
- `prod`: for production, with MySQL database.

Go to `target` directory and execute `java -Dspring.profiles.active=prod -jar mdd-api-1.0.0.jar`.

You can use an app like Postman to test the endpoints.

## Folder structure
```bash
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── openclassrooms
│   │   │           └── mddapi
│   │   │               ├── controller
│   │   │               ├── dto
│   │   │               ├── exception
│   │   │               ├── mapper
│   │   │               ├── model
│   │   │               ├── payload
│   │   │               │   ├── request
│   │   │               │   └── response
│   │   │               ├── repository
│   │   │               ├── security
│   │   │               │   ├── jwt
│   │   │               │   └── services
│   │   │               └── service
│   │   └── resources
│   └── test
│       └── java
│           └── com
│               └── openclassrooms
│                   └── mddapi
│                       └── integration
│                           ├── mapper
│                           ├── repository
│                           └── service
```

## Tests
A few tests are implemented.

Execute `mvn surefire-report:report` to generate the report.

Navigate to `target/site/surefire-report.html` to see the tests report.

## Documentation
### Javadoc
Execute `mvn javadoc:javadoc` to generate the javadoc.

Navigate to `target/site/apidocs/index.html` to see the documentation.

### Swagger
To see the API documentation, when this app is launched, in your web browser go to `http://localhost:9000/api/swagger-ui/index.html`

## Author
Gilles BERNARD (@tipikae)