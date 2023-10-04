package com.example.libraryweb.data.repositories;

import com.example.libraryweb.data.modules.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
