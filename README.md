# 📚 Book Search Engine

This project is a **robust and secure backend** for a book search engine, meticulously crafted with **Spring Boot**, **Docker**, and **PostgreSQL**. It offers a comprehensive set of features for managing and exploring a vast collection of books, alongside a secure authentication and authorization system. 🔍

---

## ✨ Features

* **🔎 Advanced Book Search**: Effortlessly search for books using a general **search term**, or drill down with specific queries by **ISBN**, **Author's Name**, or **Title**.
* **➕ Comprehensive Book Management**:
    * **Add Books**: Seamlessly introduce new books with detailed metadata into the database.
    * **Update Books**: Keep your library's information current by modifying existing book details.
    * **Delete Books**: Maintain a clean and organized database by removing books.
* **👤 Secure User Authentication**:
    * **Login**: Authenticate users and issue secure JWTs for subsequent requests.
    * **Sign-Up**: Allow new users to register and gain access to the system.

* **🔐 Granular Role Management**: Empower administrators with the ability to **assign and revoke admin privileges** for other users, ensuring secure access control.
* **🔄 Custom Error Handling**: Receive clear, descriptive **JSON error messages** for unauthorized (401) and forbidden (403) access attempts, enhancing API usability.
* **📈 Scalable & Maintainable Design**: Built on the robust Spring Boot framework, ensuring easy extensibility and adherence to modern architectural best practices.

---

## 💻 Tech Stack

* **🖥️ Backend**: Java with **Spring Boot** (latest stable version)
* **📂 Database**: **PostgreSQL** (Relational Database)
* **🐳 Containerization**: **Docker** & **Docker Compose**

---

## 🚀 Getting Started

To get your Book Search Engine up and running, follow these simple steps:

### Prerequisites

* **Docker Desktop** (or Docker Engine & Docker Compose)
* **Java 17 or higher** (LTS version recommended for Spring Boot 3+)
* **Apache Maven**

### Running the Application

1.  **Clone the Repository**:
    Start by getting the project code onto your local machine:
    ```sh
    git clone [https://github.com/asadsaud25/Book-Search-Engine.git](https://github.com/asadsaud25/Book-Search-Engine.git)
    cd book-search-engine
    ```

2. **Launch Docker Containers**:
    This command will spin up your PostgreSQL database in a Docker container:
    ```sh
    docker-compose up -d
    ```
    *Troubleshooting:* If you encounter issues, ensure Docker Desktop is running and there are no port conflicts (e.g., another service already using port 5432).


3. **Run the Spring Boot Application**:
    Start the backend server:
    ```sh
    mvn exec:java -Dexec.mainClass="com.h2.App"
    ```
    Your application should now be accessible, typically at `http://localhost:8080`.

---

### Inserting Bulk CSV Data for Testing

To quickly populate your database with test data, you can use the provided `DbImporter.java` utility.

1.  **Ensure the Database is Running**:
    If not already, start your PostgreSQL container:
    ```sh
    docker-compose up -d
    ```

2.  **Run the `DbImporter`**:
    ```sh
    mvn exec:java -Dexec.mainClass="com.h2.DBImporter"
    ```
    This will connect to your running database and insert the book data.

---
## 🔌 API Endpoints

All endpoints are hosted at `http://localhost:8080`. For endpoints requiring authentication, include your JWT in the `Authorization` header as `Bearer <YOUR_JWT_TOKEN>`.

### Authentication & Authorization Endpoints

* **User Login**
    `POST /auth/login`
    Authenticates a user and provides an access token.
    * **Request Body:**
        ```json
        {
          "email": "user@example.com",
          "password": "yourPassword"
        }
        ```
    * **Response (200 OK):** JWT access token and basic user details.
        ```json
        {
          "accessToken": "eyJhbGciOiJIUzI1Ni...",
          "id": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
          "username": "user",
          "email": "user@example.com",
          "isAdmin": false
        }
        ```

* **User Sign-Up**
    `POST /auth/signIn`
    Registers a new user and automatically logs them in.
    * **Request Body:**
        ```json
        {
          "username": "newuser",
          "email": "newuser@example.com",
          "password": "yourPassword"
        }
        ```
    * **Response (200 OK):** JWT access token and newly created user details.
        ```json
        {
          "accessToken": "eyJhbGciOiJIUzI1Ni...",
          "id": "09876543-2109-fedc-ba98-76543210abcd",
          "username": "newuser",
          "email": "newuser@example.com",
          "isAdmin": false,
          "createdAt": "2024-06-10T10:30:00Z"
        }
        ```

* **Admin - Update User Role**
    `PUT /admin/changeRole`
    **Requires Admin Role (ROLE_ADMIN)**. Updates the `isAdmin` status for a specified user.
    * **Request Body:**
        ```json
        {
          "username": "target_username",
          "isAdmin": true
        }
        ```
    * **Response (200 OK):** Updated user role details.

* **Get Authenticated User Details**
    `GET /secured`
    **Requires Authentication**. Returns the details of the currently authenticated user.
    * **Response (200 OK):** User's ID and assigned roles.
        ```json
        {
          "userId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
          "email": "user@example.com",
          "roles": ["ROLE_USER"]
        }
        ```

### Book Management Endpoints (Require Authentication)

* **Search Books (General)**
    `GET /books/search?searchTerm={searchTerm}`
    **Requires Authentication**. Searches for books by title, author, or description.
    * Example: `GET /books/search?searchTerm=Python`
    * **Response (200 OK):** A list of books matching the search term.

* **Add Book**
    `POST /books/add`
    **Requires Authentication**. Adds a new book entry to the database.
    * **Request Body:**
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
    * **Response (200 OK):** The added book details.

* **Update Book**
    `PUT /books/update/{isbn}`
    **Requires Authentication**. Updates an existing book identified by its ISBN.
    * **Request Body:** (Same structure as Add Book)
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
    * **Response (200 OK):** The updated book details.

* **Delete Book**
    `DELETE /books/delete/{isbn}`
    **Requires Authentication**. Deletes a book identified by its ISBN.
    * Example: `DELETE /books/delete/123-456-7890`
    * **Response (200 OK):** A message indicating successful deletion.

* **Search Book by ISBN**
    `GET /books/search/isbn/{isbn}`
    **Requires Authentication**. Finds a single book by its exact ISBN.
    * Example: `GET /books/search/isbn/123-456-7890`
    * **Response (200 OK):** The book details matching the ISBN.

* **Search Books by Author**
    `GET /books/search/author?authorName={authorName}`
    **Requires Authentication**. Finds books by a specific author's name.
    * Example: `GET /books/search/author?authorName=James Gosling`
    * **Response (200 OK):** A list of books by the author.

* **Search Books by Title**
    `GET /books/search/title?title={title}`
    **Requires Authentication**. Finds books by their title.
    * Example: `GET /books/search/title?title=Java Programming`
    * **Response (200 OK):** A list of books matching the title.

---

### Running Tests

To execute the project's test suite, use the following Maven command:
```sh
mvn test
```

## 📄 License
This project is licensed under the MIT License - [MIT LICENSE](LICENSE.md)

