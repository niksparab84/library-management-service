# library-management-service
Simple Library System for Ascendion Java Assessment

## How to run the application
1. Clone the repository:
   ```bash
   git clone library-management-service
2. Navigate to the project directory on your terminal:
   ```bash
   cd library-management-service:
3. Build the project using Maven:
   ```bash
    mvn clean install
4. Run the application using Docker Compose as follows:
   Note: Make sure you have Docker installed and running on your machine.
   Postgresql database is integrated in this project and docker-compose.yml file is provided to run the database.
   Run the following command in the terminal:
    ```bash
   # Build Docker image for Spring Boot application #
   docker build -t library-management-service-image -f Dockerfile .
   # Build Docker image for PostgreSQL
   docker build -t postgres-image -f Dockerfile-postgres .
   # To see the images
   docker images
   # Run the docker compose to start the application and database
   docker-compose up -d

## API Details:
- **Base URL**: http://localhost:8080/library-management-service/api/
- **API Version**: v1
- **Content Type**: application/json
******************************************************************************************
- **Name**: Register New Book
- **Description**: Registers a new book in the library.
- **Endpoint**: POST /books/create
- **URL**: http://localhost:8080/library-management-service/api/books/create
- **Request Body**:
```json
{
  "title": "Book Title",
  "author": "Author Name",
  "isbn": "1234567890"
}
```
- **Response**:
```json
{
  "id": 1,
  "title": "Book Title",
  "author": "Author Name",
  "isbn": "1234567890",
  "borrower": null
}
```
******************************************************************************************
- **Name**: Get All Books
- **Description**: Get a list of all books in the library
- **Endpoint**: GET /books/all
- **URL**: http://localhost:8080/library-management-service/api/books/all
- **Response**:
```json
[
  {
    "id": 1,
    "title": "Book Title",
    "author": "Author Name",
    "isbn": "1234567890",
    "borrower": null
  },
  {
    "id": 2,
    "title": "Another Book",
    "author": "Another Author",
    "isbn": "0987654321",
    "borrower": null
  }
]
```
******************************************************************************************

