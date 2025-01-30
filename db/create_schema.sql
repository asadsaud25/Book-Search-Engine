-- We are using PostgreSQL 16.4

-- CSV Header is below
-- bookId,title,author,rating,description,language,isbn,bookFormat,edition,pages,publisher,publishDate,firstPublishDate,likedPercent,price

-- Create the books table
CREATE TABLE books (
    SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    rating DECIMAL(2,1) NOT NULL,
    description TEXT NOT NULL,
    language VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    book_format VARCHAR(255) NOT NULL,
    edition VARCHAR(255) NOT NULL,
    pages INT NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    publish_date DATE NOT NULL,
    first_publish_date DATE NOT NULL,
    liked_percent DECIMAL(5,2) NOT NULL,
    price DECIMAL(5,2) NOT NULL
);

-- for full text search
UPDATE books SET search_vector = 
setweight(to_tsvector('english', coalesce(title,'')), 'A') ||
setweight(to_tsvector('english', coalesce(description,'')), 'B') ||
setweight(to_tsvector('english', coalesce(isbn,'')), 'C');

-- Create the authors table
CREATE TABLE authors (
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create the books_authors table
CREATE TABLE books_authors (
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id),
    FOREIGN KEY (author_id) REFERENCES authors (author_id)
);

CREATE OR REPLACE FUNCTION update_search_vector()
RETURNS TRIGGER AS $$
BEGIN
  NEW.search_vector := 
    setweight(to_tsvector('english', coalesce(NEW.title, '')), 'A') ||
    setweight(to_tsvector('english', coalesce(NEW.description, '')), 'B') ||
    setweight(to_tsvector('english', coalesce(NEW.isbn, '')), 'C');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_search_vector_trigger
BEFORE INSERT OR UPDATE ON books
FOR EACH ROW
EXECUTE FUNCTION update_search_vector();

-- INSERT INTO books (title, description, isbn)
-- VALUES ('Test Book', 'This is a test description', '1234567890');

-- SELECT title, description, isbn, search_vector
-- FROM books
-- WHERE title = 'Test Book';

-- UPDATE books
-- SET title = 'Updated Book Title'
-- WHERE isbn = '1234567890';

-- SELECT title, description, isbn, search_vector
-- FROM books
-- WHERE isbn = '1234567890';

UPDATE books
SET search_vector = 
  setweight(to_tsvector('english', coalesce(title, '')), 'A') ||
  setweight(to_tsvector('english', coalesce(description, '')), 'B') ||
  setweight(to_tsvector('english', coalesce(isbn, '')), 'C');


-- to restrict user to add books with same isbn
ALTER TABLE books ADD CONSTRAINT unique_isbn UNIQUE (isbn); 

-- for better better readability and maintainability.
CREATE VIEW book_with_authors AS
SELECT 
    b.book_id AS bookId, 
    b.title, 
    b.rating, 
    b.description, 
    b.language, 
    b.isbn, 
    b.book_format AS bookFormat, 
    b.edition, 
    b.pages, 
    b.publisher, 
    b.publish_date AS publishDate, 
    b.first_publish_date AS firstPublishDate, 
    b.liked_percent AS likedPercent, 
    b.price, 
    STRING_AGG(a.name, ', ') AS authors,  
    b.search_vector::text AS searchVector  
FROM books b
JOIN books_authors ba ON b.book_id = ba.book_id
JOIN authors a ON ba.author_id = a.author_id
GROUP BY b.book_id, b.search_vector;
