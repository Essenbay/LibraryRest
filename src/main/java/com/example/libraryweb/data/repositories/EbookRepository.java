package com.example.libraryweb.data.repositories;

import com.example.libraryweb.data.modules.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<Ebook, Long> {
}
