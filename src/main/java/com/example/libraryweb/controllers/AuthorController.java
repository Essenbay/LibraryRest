package com.example.libraryweb.controllers;

import com.example.libraryweb.data.modules.*;
import com.example.libraryweb.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        return author.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("")
    public Author createAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor.isPresent()) {
            author.setId(existingAuthor.get().getId());
            Author updatedAuthor = authorService.saveAuthor(author);
            return ResponseEntity.ok().body(updatedAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
    }
}
