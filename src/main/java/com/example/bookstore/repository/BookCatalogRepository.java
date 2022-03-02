package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCatalogRepository extends JpaRepository<Book,Long>{
    Optional<Book> findByIsbn(String isbn);
}
