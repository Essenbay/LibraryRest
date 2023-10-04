package com.example.libraryweb.controllers;

import com.example.libraryweb.data.modules.*;
import com.example.libraryweb.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Optional<Genre> genre = genreService.getGenreById(id);
        return genre.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("")
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        Optional<Genre> existingGenre = genreService.getGenreById(id);
        if (existingGenre.isPresent()) {
            genre.setId(existingGenre.get().getId());
            Genre updatedGenre = genreService.saveGenre(genre);
            return ResponseEntity.ok().body(updatedGenre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenreById(id);
    }

}
