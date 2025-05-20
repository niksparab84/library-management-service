# library-management-service
Simple Library System for Ascendion Java Assessment

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
  "isbn": "1234567890"
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
    "isbn": "1234567890"
  },
  {
    "id": 2,
    "title": "Another Book",
    "author": "Another Author",
    "isbn": "0987654321"
  }
]
```
******************************************************************************************

