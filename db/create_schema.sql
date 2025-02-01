-- We are using PostgreSQL 16.4

-- CSV Header is below
-- bookId,title,author,rating,description,language,isbn,bookFormat,edition,pages,publisher,publishDate,firstPublishDate,likedPercent,price

-- Create the authors table (This needs to be created FIRST due to foreign key constraints)
CREATE TABLE IF NOT EXISTS authors (
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create the books table
CREATE TABLE IF NOT EXISTS books (
    book_id SERIAL PRIMARY KEY,  -- Explicitly name the primary key column
    title VARCHAR(255) NOT NULL,
    rating DECIMAL(2,1) NOT NULL,
    description TEXT NOT NULL,
    language VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE, -- Unique constraint directly on the column
    book_format VARCHAR(255) NOT NULL,
    edition VARCHAR(255) NOT NULL,
    pages INT NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    publish_date DATE NOT NULL,
    first_publish_date DATE NOT NULL,
    liked_percent DECIMAL(5,2) NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    search_vector tsvector -- Add the search_vector column
);


-- Create the books_authors table
CREATE TABLE IF NOT EXISTS books_authors (
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id),
    FOREIGN KEY (author_id) REFERENCES authors (author_id)
);

-- Function to update the search vector
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

-- Trigger to automatically update the search vector
CREATE OR REPLACE TRIGGER update_search_vector_trigger  -- Use CREATE OR REPLACE
BEFORE INSERT OR UPDATE ON books
FOR EACH ROW
EXECUTE FUNCTION update_search_vector();


-- Initial update of search_vector for existing data (if any)
-- This is necessary if you are migrating data or adding the search_vector
-- column to an existing table.  It's safe to run even on a new database.
-- It will just not do anything if the table is empty.

-- Create the book_with_authors view (CREATE OR REPLACE)
CREATE OR REPLACE VIEW book_with_authors AS
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

-- Create the index on the search_vector column (GIN index)
-- it is important to create an index on the search_vector column for efficient searching
CREATE INDEX idx_search_vector ON books USING gin (search_vector);