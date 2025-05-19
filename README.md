# library-management-service
Simple Library System for Ascendion Java Assessment

## API Details:
- **Base URL**: http://localhost:8080/library-management-service/api/
- **API Version**: v1
- **Content Type**: application/json

- Register a new book to the library:
- http://localhost:8080/library-management-service/api/books/create
- **Endpoint**: POST /books/create
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
  "isbn": "1234567890"
}
```
- **Description**: Registers a new book in the library.

- Get a list of all books in the library.
- http://localhost:8080/library-management-service/api/books/all
- **Endpoint**: GET /books/all
- **Response**:
```json
[
  {
    "id": 1,
    "title": "Book Title",
    "author": "Author Name",
    "isbn": "1234567890",
    "publishedDate": "2023-01-01"
  },
  {
    "id": 2,
    "title": "Another Book",
    "author": "Another Author",
    "isbn": "0987654321",
    "publishedDate": "2023-02-01"
  }
]
```
- **Description**: Retrieves a list of all books in the library.

