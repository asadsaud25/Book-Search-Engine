-- Create the authors sequence
CREATE SEQUENCE IF NOT EXISTS public.authors_author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.authors_author_id_seq OWNER TO admin;

-- Create the authors table
CREATE TABLE IF NOT EXISTS public.authors (
    author_id integer NOT NULL DEFAULT nextval('public.authors_author_id_seq'::regclass),
    name character varying(255) NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (author_id),
    CONSTRAINT authors_name_unique UNIQUE (name)
);

ALTER TABLE public.authors OWNER TO admin;
ALTER SEQUENCE public.authors_author_id_seq OWNED BY public.authors.author_id;


-- Create the books sequence
CREATE SEQUENCE IF NOT EXISTS public.books_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.books_book_id_seq OWNER TO admin;

-- Create the books table
CREATE TABLE IF NOT EXISTS public.books (
    book_id bigint NOT NULL DEFAULT nextval('public.books_book_id_seq'::regclass),
    title character varying(255) NOT NULL,
    rating numeric(38,2),
    description text,
    language character varying(255),
    isbn character varying(255),
    book_format character varying(255),
    edition character varying(255),
    pages integer,
    publisher character varying(255),
    publish_date date,
    first_publish_date date,
    liked_percent numeric(38,2),
    price numeric(38,2),
    search_vector tsvector,
    CONSTRAINT books_pkey PRIMARY KEY (book_id),
    CONSTRAINT unique_isbn UNIQUE (isbn)
);

ALTER TABLE public.books OWNER TO admin;
ALTER SEQUENCE public.books_book_id_seq OWNED BY public.books.book_id;


-- Create the books_authors table
CREATE TABLE IF NOT EXISTS public.books_authors (
    book_id integer NOT NULL,
    author_id integer NOT NULL,
    CONSTRAINT book_authors_pkey PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (author_id) REFERENCES public.authors(author_id),
    FOREIGN KEY (book_id) REFERENCES public.books(book_id)
);

ALTER TABLE public.books_authors OWNER TO admin;

-- Create the update_search_vector function (must be before the trigger)
CREATE OR REPLACE FUNCTION public.update_search_vector() RETURNS trigger
    LANGUAGE plpgsql
AS $$
BEGIN
    NEW.search_vector :=
        setweight(to_tsvector('english', coalesce(NEW.title, '')), 'A') ||
        setweight(to_tsvector('english', coalesce(NEW.description, '')), 'B') ||
        setweight(to_tsvector('english', coalesce(NEW.isbn, '')), 'C');
    RETURN NEW;
END;
$$;

ALTER FUNCTION public.update_search_vector() OWNER TO admin;

-- Create the trigger (depends on the function)
CREATE OR REPLACE TRIGGER update_search_vector_trigger
BEFORE INSERT OR UPDATE ON public.books
FOR EACH ROW EXECUTE FUNCTION public.update_search_vector();


-- Create the book_with_authors view (depends on tables)
CREATE OR REPLACE VIEW public.book_with_authors AS
SELECT b.book_id AS bookid,
    b.title,
    b.rating,
    b.description,
    b.language,
    b.isbn,
    b.book_format AS bookformat,
    b.edition,
    b.pages,
    b.publisher,
    b.publish_date AS publishdate,
    b.first_publish_date AS firstpublishdate,
    b.liked_percent AS likedpercent,
    b.price,
    string_agg((a.name)::text, ', '::text) AS authors,
    (b.search_vector)::text AS search_vector
FROM ((public.books b
    JOIN public.books_authors ba ON ((b.book_id = ba.book_id)))
    JOIN public.authors a ON ((ba.author_id = a.author_id)))
GROUP BY b.book_id, b.search_vector;

ALTER VIEW public.book_with_authors OWNER TO admin;

-- Create indexes (can be last)
CREATE INDEX IF NOT EXISTS books_search_idx ON public.books USING gin (search_vector);
CREATE INDEX IF NOT EXISTS idx_search_vector ON public.books USING gin (search_vector);