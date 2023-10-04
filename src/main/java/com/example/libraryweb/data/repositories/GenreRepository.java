package com.example.libraryweb.data.repositories;

import com.example.libraryweb.data.modules.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
