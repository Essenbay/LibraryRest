package com.example.libraryweb.controllers;

import com.example.libraryweb.data.modules.*;
import com.example.libraryweb.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final EbookService ebookService;
    private final GenreService genreService;
    private final TextbookService textbookService;

    @GetMapping("")
    public Map<String, Object> getAllBooks() {
        List<Textbook> textBooks = new ArrayList<>(textbookService.getAllTextbooks());
        List<Ebook> ebooks = new ArrayList<>(ebookService.getAllEbooks());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("textbooks", textBooks);
        responseMap.put("ebooks", ebooks);

        return responseMap;
    }


    @GetMapping("/textbooks")
    public List<Textbook> getAllTextBooks() {
        return new ArrayList<>(textbookService.getAllTextbooks());
    }

    @GetMapping("/ebooks")
    public List<Ebook> getAllEBooks() {
        return new ArrayList<>(ebookService.getAllEbooks());
    }


    @GetMapping("/textbooks/{id}")
    public ResponseEntity<Textbook> getTextBookById(@PathVariable Long id) {
        Optional<Textbook> textbook = textbookService.getTextbookById(id);
        return textbook.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/textbooks")
    public ResponseEntity<Object> createTextBook(
            @RequestBody TextbookDto textbook
    ) {
        final Textbook newTextbook = new Textbook();
        if (updateBook(textbook, newTextbook)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(textbookService.saveTextbook(newTextbook));
    }

    @PutMapping("/textbooks/{id}")
    public ResponseEntity<Object> updateTextBook(
            @PathVariable Long id,
            @RequestBody TextbookDto textbook
    ) {
        Optional<Textbook> textbookOptional = textbookService.getTextbookById(id);

        if (textbookOptional.isPresent()) {
            Textbook updatedTextbook = textbookOptional.get();
            if (updateBook(textbook, updatedTextbook)) return ResponseEntity.notFound().build();

            textbookService.saveTextbook(updatedTextbook);
            return ResponseEntity.ok().body(updatedTextbook);

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    private boolean updateBook(@RequestBody TextbookDto textbook, Textbook updatedTextbook) {
        updatedTextbook.setTitle(textbook.title);
        updatedTextbook.setAvailable(textbook.available);
        updatedTextbook.setEdition(textbook.edition);
        Optional<Author> author = authorService.getAuthorById(textbook.authorId);
        if(author.isPresent()) {
            updatedTextbook.setAuthor(author.get());
        } else {
            return true;
        }

        Optional<Genre> genre = genreService.getGenreById(textbook.genreId);
        if(genre.isPresent()) {
            updatedTextbook.setGenre(genre.get());

        } else {
            return true;
        }
        return false;
    }

    private boolean updateEBook(@RequestBody EbookDto ebookDto, Ebook updatedEbook) {
        updatedEbook.setTitle(ebookDto.title);
        updatedEbook.setSize(ebookDto.size);
        updatedEbook.setFormat(ebookDto.format);
        Optional<Author> author = authorService.getAuthorById(ebookDto.authorId);
        if(author.isPresent()) {
            updatedEbook.setAuthor(author.get());
        } else {
            return true;
        }

        Optional<Genre> genre = genreService.getGenreById(ebookDto.genreId);
        if(genre.isPresent()) {
            updatedEbook.setGenre(genre.get());

        } else {
            return true;
        }
        return false;
    }


    @GetMapping("/ebooks/{id}")
    public ResponseEntity<Ebook> getEbookById(@PathVariable Long id) {
        Optional<Ebook> ebook = ebookService.getEbookById(id);
        return ebook.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/ebooks")
    public ResponseEntity<Object> createEbook(
            @RequestBody EbookDto ebook) {
        final Ebook newEbook = new Ebook();
        if (updateEBook(ebook, newEbook)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ebookService.saveEbook(newEbook));
    }

    @PutMapping("/ebooks/{id}")
    public ResponseEntity<Object> updateEBook(
            @PathVariable Long id,
            @RequestBody EbookDto ebookDto
    ) {
        Optional<Ebook> textbookOptional = ebookService.getEbookById(id);

        if (textbookOptional.isPresent()) {
            Ebook updatedTextbook = textbookOptional.get();
            if (updateEBook(ebookDto, updatedTextbook)) return ResponseEntity.notFound().build();

            ebookService.saveEbook(updatedTextbook);
            return ResponseEntity.ok().body(updatedTextbook);

        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }
}
