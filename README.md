# Spring Boot Blog Post CRUD Backend

This is a Spring Boot application for managing blog posts, including full CRUD (Create, Read, Update, Delete) functionality. The project is built using **Spring Boot 3.3**, **Java 17**, and **MySQL 8.0**, and it is deployed on **AWS Elastic Beanstalk** and **AWS RDS**.

## Table of Contents

- [Technologies](#technologies)
- [Project Setup](#project-setup)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [Deploying to AWS](#deploying-to-aws)
- [API Endpoints](#api-endpoints)

## Technologies

- **Java 17**
- **Spring Boot 3.3**
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**
- **AWS Elastic Beanstalk**
- **AWS RDS**

## Project Setup

1. **Clone the Repository**

   ```bash
   git clone <repository-url>
   cd blog-post-backend
   ```

2. **Maven Build**

   Ensure you have Maven and Java 17 installed on your machine. To build the project, run:

   ```bash
   mvn clean install
   ```

3. **Spring Boot Dependencies**

   This project uses Spring Boot with the following key dependencies:

   - Spring Web
   - Spring Data JPA
   - MySQL Connector
   - Lombok (optional for reducing boilerplate code)

   The dependencies are defined in the `pom.xml` file:

   ```xml
   <dependencies>
       <!-- Spring Boot Starter Web -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>

       <!-- Spring Data JPA for database interactions -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-jpa</artifactId>
       </dependency>

       <!-- MySQL Driver -->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <scope>runtime</scope>
       </dependency>

       <!-- Lombok -->
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <scope>provided</scope>
       </dependency>
   </dependencies>
   ```

## Database Configuration

Configure the connection to your MySQL database in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<RDS-endpoint>:3306/<database-name>
spring.datasource.username=<db-username>
spring.datasource.password=<db-password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Running the Application

To run the application locally, use the following command:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## Deploying to AWS

### AWS Elastic Beanstalk

1. **Create an Elastic Beanstalk Application**

   - Navigate to the AWS Elastic Beanstalk console.
   - Create a new application.
   - Choose "Java" as the platform.
   - Upload your `jar` file (found in `target` folder after running `mvn clean package`).

2. **Configure Environment Variables**

   Add the necessary environment variables in Elastic Beanstalk for database connectivity:

   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`

### AWS RDS

1. **Create RDS Database**

   - Navigate to the AWS RDS console.
   - Create a new MySQL instance.
   - Note down the endpoint, username, and password for configuration.

2. **Configure Security Groups**

   Ensure that the Elastic Beanstalk security group can communicate with the RDS instance.

## API Endpoints

### Blog Post Endpoints

| Method | Endpoint             | Description                     |
|--------|----------------------|---------------------------------|
| GET    | `/api/posts`          | Get all blog posts              |
| GET    | `/api/posts/{id}`     | Get blog post by ID             |
| POST   | `/api/posts`          | Create a new blog post          |
| PUT    | `/api/posts/{id}`     | Update an existing blog post    |
| DELETE | `/api/posts/{id}`     | Delete a blog post by ID        |

### Sample JSON for Creating a Blog Post

```json
{
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post."
}
```

## Conclusion

This Spring Boot CRUD backend project provides a foundation for managing blog posts with MySQL as the database. It's deployed on AWS Elastic Beanstalk with RDS for database storage. You can extend this project by adding more features such as authentication, pagination, or search functionality.

