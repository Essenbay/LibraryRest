package com.example.libraryweb.services;

import com.example.libraryweb.data.modules.Book;
import com.example.libraryweb.data.modules.BookCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    public CartService(HttpSession session, BookService bookService) {
        this.session = session;
        this.bookService = bookService;
    }

    private final HttpSession session;
    private final BookService bookService;
    private static final String SESSION_CART_KEY = "bookCart";

    public void addBookToCart(Long bookId) {
        BookCart cart = (BookCart) session.getAttribute(SESSION_CART_KEY);
        if (cart == null) {
            cart = new BookCart();
        }
        Book book = bookService.getBookById(bookId);
        cart.addBook(book);
        session.setAttribute(SESSION_CART_KEY, cart);
    }

    public void removeBookFromCart(Long bookById) {
        BookCart cart = (BookCart) session.getAttribute(SESSION_CART_KEY);
        if (cart != null) {
            Book book = bookService.getBookById(bookById);
            cart.removeBook(book);
            session.setAttribute(SESSION_CART_KEY, cart);
        }
    }

    public BookCart getBookCart() {
        BookCart cart = (BookCart) session.getAttribute(SESSION_CART_KEY);
        if (cart == null) {
            cart = new BookCart();
            session.setAttribute(SESSION_CART_KEY, cart);
        }
        return cart;
    }

    public void clearShoppingCart() {
        session.removeAttribute(SESSION_CART_KEY);
    }
}
