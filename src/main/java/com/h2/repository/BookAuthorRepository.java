package com.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.h2.entity.BookAuthor;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    // No custom methods required for now
}
