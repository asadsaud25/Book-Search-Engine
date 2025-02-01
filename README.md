# 📚 Book Search Engine

This is a **basic search engine** for books built using **Spring Boot**, **Docker**, and **PostgreSQL**. The current implementation allows users to search for books by a given search term, add new books to the database, update existing books, and delete books. 🔍

## ✨ Features
- **🔎 Search Books**: A single endpoint to search for books by a search term.
- **➕ Add Books**: An endpoint to add new books to the database.
- **✏️ Update Books**: An endpoint to update existing books in the database.
- **🗑️ Delete Books**: An endpoint to delete books from the database.
- **🔍 Search Books by ISBN**: An endpoint to search for a book by its ISBN.
- **🔍 Search Books by Author**: An endpoint to search for books by an author's name.
- **🔍 Search Books by Title**: An endpoint to search for books by their title.
- **📈 Scalable Design**: Built with Spring Boot, making it easy to add more features in the future.

## 💻 Tech Stack
- **🖥️ Backend**: Java with Spring Boot
- **📂 Database**: PostgreSQL
- **🐳 Containerization**: Docker

## 🚀 Getting Started

### Prerequisites
- Docker 
- Java 11 or higher
- Maven

### Running the Application

1. **Clone the repository**:
    ```sh
    git clone https://github.com/asadsaud25/Book-Search-Engine.git
    cd book-search-engine
    ```

2. **Run the Docker containers**:
    ```sh
    docker-compose up -d
    ```

3. **Build the project**:
    ```sh
    mvn clean install
    ```

4. **Run the Spring Boot application**:
    ```sh
    mvn spring-boot:run
    ```

### Inserting Bulk CSV Data for Testing

For testing purposes, you can use the `DbImporter.java` file to insert bulk CSV data from [github gist](https://gist.github.com/hhimanshu/d55d17b51e0a46a37b739d0f3d3e3c74/raw/5b9027cf7b1641546c1948caffeaa44129b7db63/books.csv) into the database.

1. **Ensure the database is running**:
    ```sh
    docker-compose up -d
    ```

2. **Run the `DbImporter.java` file**:
    ```sh
    mvn exec:java -Dexec.mainClass="com.h2.DBImporter"
    ```

### API Endpoints

- **Search Books**: `GET /books/search?searchTerm={searchTerm}`
    - Example: `GET /books/search?searchTerm=Python`
    - Response: A list of books matching the search term.

- **Add Book**: `POST /books/add`
    - Request Body:
        ```json
        {
            "title": "Java Programming",
            "description": "A comprehensive guide to Java programming.",
            "isbn": "123-456-7890",
            "rating": 4.5,
            "language": "English",
            "bookFormat": "PDF",
            "edition": "1st",
            "pages": 500,
            "publisher": "Oracle",
            "publishDate": "2021-01-01",
            "firstPublishDate": "2020-01-01",
            "likedPercent": 90,
            "price": 150.00,
            "authors": ["James Gosling"]
        }
        ```
    - Response: The added book details.

- **Update Book**: `PUT /books/update/{isbn}`
    - Request Body:
        ```json
        {
            "title": "Advanced Java Programming",
            "description": "An advanced guide to Java programming.",
            "rating": 4.8,
            "language": "English",
            "bookFormat": "Hardcover",
            "edition": "2nd",
            "pages": 600,
            "publisher": "Oracle",
            "publishDate": "2022-01-01",
            "firstPublishDate": "2020-01-01",
            "likedPercent": 95,
            "price": 200.00,
            "authors": ["James Gosling"]
        }
        ```
    - Response: The updated book details.

- **Delete Book**: `DELETE /books/delete/{isbn}`
    - Example: `DELETE /books/delete/123-456-7890`
    - Response: A message indicating the book was successfully deleted.

- **Search Book by ISBN**: `GET /books/search/{isbn}`
    - Example: `GET /books/search/123-456-7890`
    - Response: The book details matching the ISBN.

- **Search Books by Author**: `GET /books/search/author?authorName={authorName}`
    - Example: `GET /books/search/author?authorName=James Gosling`
    - Response: A list of books matching the author's name.

- **Search Books by Title**: `GET /books/search/title?title={title}`
    - Example: `GET /books/search/title?title=Java Programming`
    - Response: A list of books matching the title.

## 🛠️ Development

### Running Tests
To run the tests, use the following command:
```sh
mvn test
```

## 📄 License
This project is licensed under the MIT License - [MIT LICENSE](LICENSE.md)

## 🔮 Future Plans
### Security
- **Authentication and Authorization**: Implement Spring Security to secure the API endpoints.
- **Role-Based Access Control**: Define roles and permissions to control access to different parts of the application.
- **OAuth2/JWT**: Integrate OAuth2 and JWT for token-based authentication.