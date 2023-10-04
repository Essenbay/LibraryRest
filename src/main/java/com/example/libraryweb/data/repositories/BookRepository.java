package com.example.libraryweb.data.repositories;

import com.example.libraryweb.data.modules.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

