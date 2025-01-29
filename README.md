# 📚 Book Search Engine

This is a **basic search engine** for books built using **Spring Boot**, **Docker**, and **PostgreSQL**. The current implementation allows users to search for books by a given search term and add new books to the database. 🔍

## ✨ Features
- **🔎 Search Books**: A single endpoint to search for books by a search term.
- **➕ Add Books**: An endpoint to add new books to the database.
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
    cd Book-Search-Engine
    ```

2. **Build the project**:
    ```sh
    mvn clean install
    ```

3. **Run the Docker containers**:
    ```sh
    docker-compose up -d
    ```

4. **Run the Spring Boot application**:
    ```sh
    mvn spring-boot:run
    ```

### API Endpoints

- **Search Books**: `GET /books/search?searchTerm={searchTerm}`
    - Example: `GET /books/search?searchTerm=coding`
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

## 🛠️ Development

### Running Tests
To run the tests, use the following command:
```sh
mvn test
```
📄 License
This project is licensed under the MIT License - [MIT LICENSE](LICENSE.md)