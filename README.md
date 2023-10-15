# Bookstore API

This is a simple Java application that implements a RESTful API for an online bookstore. It allows users to perform CRUD operations on books, authors, and genres.

## How to Run

1. Clone this repository.
2. Set up a PostgreSQL database and update the `application.properties` file with the database details.
3. Build the project using Maven: `mvn clean install`.
4. Run the application: `java -jar target/bookstore-api-1.0.0.jar`.

## Database Configuration

Edit the `application.properties` file to configure the PostgreSQL database:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=your_username
spring.datasource.password=your_password
