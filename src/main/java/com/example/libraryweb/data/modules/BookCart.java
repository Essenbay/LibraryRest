package com.example.libraryweb.data.modules;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookCart {
    private List<Book> books;

    public BookCart() {
        this.books = new ArrayList<>(0);
    }

    public BookCart(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void clear() {
        books.clear();
    }

    public int getBookCount() {
        return books.size();
    }

}
