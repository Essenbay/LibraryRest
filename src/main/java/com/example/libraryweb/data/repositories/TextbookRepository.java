package com.example.libraryweb.data.repositories;

import com.example.libraryweb.data.modules.Textbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextbookRepository extends JpaRepository<Textbook, Long> {
}
