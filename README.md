🎬 Movie API

A simple RESTful API built with Spring Boot and MySQL to manage a collection of movies.
Supports CRUD operations: Create, Read, Update, Delete, along with partial updates using PATCH.

🛠️ Technologies Used

Backend: Java 21, Spring Boot, Spring Data JPA

Database: MySQL

Build Tool: Maven

HTTP Client (for testing): Postman or curl

⚡ Features

POST /api/movies → Add a new movie

GET /api/movies → Retrieve all movies

GET /api/movies/{id} → Retrieve a single movie by ID

PUT /api/movies/{id} → Replace an existing movie

PATCH /api/movies/{id} → Update specific fields of a movie

DELETE /api/movies/{id} → Remove a movie

🏗️ Project Setup
1. Clone the repository
git clone <your-repo-url>
cd movie-api

2. Configure MySQL

Create a database:

CREATE DATABASE restapi;


Update application.properties (or application.yml) with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/restapi
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


ddl-auto=update will create the movies table automatically from your entity class.

3. Run the application
mvn spring-boot:run


Server will start at http://localhost:8080.

📦 API Usage
Create a Movie
POST /api/movies
Content-Type: application/json

{
  "title": "Dune",
  "director": "Denis Villeneuve",
  "year": 2021
}

Get All Movies
GET /api/movies

Get Movie by ID
GET /api/movies/{id}

Update Movie (Full)
PUT /api/movies/{id}
Content-Type: application/json

{
  "title": "Dune 2",
  "director": "Denis Villeneuve",
  "year": 2023
}

Update Movie (Partial)
PATCH /api/movies/{id}
Content-Type: application/json

{
  "year": 2023
}

Delete Movie
DELETE /api/movies/{id}

🔧 Testing

Use Postman, curl, or any HTTP client.

Example with curl:

curl -X POST http://localhost:8080/api/movies \
-H "Content-Type: application/json" \
-d '{"title":"Dune","director":"Denis Villeneuve","year":2021}'

⚡ Notes

The API is loosely coupled, so it can be consumed by frontends, mobile apps, or other services.

Ensure MySQL is running before starting the Spring Boot app.

The table movies is auto-generated from the Movie entity.

👤 Author

Your Name / GitHub username
