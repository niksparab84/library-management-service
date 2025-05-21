# library-management-service
Simple Library System for Ascendion Java Assessment

## How to run the application
1. Clone the repository:
   ```bash
   git clone library-management-service
2. Navigate to the project directory on your terminal:
   ```bash
   cd library-management-service
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
   it should show the images as below:
   REPOSITORY                         TAG       IMAGE ID       CREATED          SIZE
   library-management-service-image   latest    5404b8a72694   49 minutes ago   461MB   
   postgres-image                     latest    c7ee01040eb2   12 days ago      430MB

   # Run the docker compose to start the application and database
   docker-compose up -d
   
5. Access APIs:
   Refer to the API documentation below for details on how to access the APIs.

## Justify your choice of database
- I have used PostgreSQL as the database for this project. PostgreSQL is a powerful, open-source object-relational database system that has 
  strong support for advanced data types and performance optimization. It is known for its reliability, robustness, and performance, making it a suitable choice 
  for a library management system where data integrity and complex queries are essential.
- Also, PostgreSQL can be easily bundled with Spring Boot applications, and it provides excellent support for JPA (Java Persistence API), which is used in this project for data access.

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
- **Name**: Register New Borrower
- **Description**: Registers a new borrower in the library.
- **Endpoint**: POST /borrower/register
- **URL**: http://localhost:8080/library-management-service/api/borrower/register
- **Request Body**:
```json
{
  "name": "Ravi",
  "email": "Ravi@hotmail.com"
}
```
- **Response**:
```json
{
  "id": 1,
  "name": "Ravi",
  "email": "Ravi@hotmail.com"
}
```
******************************************************************************************
- **Name**: Get all Borrowers
- **Description**: Get a list of all borrowers in the library.
- **Endpoint**: GET /borrower/all
- **URL**: http://localhost:8080/library-management-service/api/borrower/all
- **Response**:
```json
[
    {
        "id": 1,
        "name": "Tom",
        "email": "Tom@hotmail.com"
    },
    {
        "id": 2,
        "name": "Ram",
        "email": "Ram@hotmail.com"
    },
    {
        "id": 3,
        "name": "Ravi",
        "email": "Ravi@hotmail.com"
    }
]
```
******************************************************************************************
- **Name**: Borrow a Book
- **Description**: Allows a borrower to borrow a book.
- **Endpoint**: POST /books/borrow
- **URL**: http://localhost:8080/library-management-service/api/books/borrow
- **Example**: http://localhost:8080/library-management-service/api/books/borrow/1?borrowerId=2
- **Path Variable**: bookId (Integer)
- **Request Parameter**: borrowerId (Integer)
- **Response**: 
```json
Book is borrowed successfully by Tom
```
******************************************************************************************
- **Name**: Return a Book
- **Description**: Allows a borrower to return a book.
- **Endpoint**: PUT /books/return
- **URL**: http://localhost:8080/library-management-service/api/books/return
- **Example**: http://localhost:8080/library-management-service/api/books/return/2
- **Path Variable**: bookId (Integer)
- **Response**: 
```json
Book is returned successfully by Ravi
```
******************************************************************************************

## Validation:
- **Book Registration**: Validates that the book title, author, and ISBN are not empty.
- **Borrower Registration**: Validates that the borrower's name and email are not empty. Email format is validated using regex. Email should be unique.
- **Book Borrowing**: Validates that the book is not already borrowed and that the borrower exists. Book ID and Borrower ID are validated.
- **Book Returning**: Validates that the book is borrowed and that the borrower exists. Book ID is validated.
- **Error Handling**: Custom error messages are provided for validation failures and other exceptions.

## Technologies Used:
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven
- Lombok
- JUnit
- Mockito

*********************************************************************************



