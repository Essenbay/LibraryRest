//package com.example.libraryweb.controllers;
//
//import com.example.libraryweb.data.modules.BookCart;
//import com.example.libraryweb.data.modules.Ebook;
//import com.example.libraryweb.data.modules.Textbook;
//import com.example.libraryweb.services.*;
//import jakarta.servlet.http.HttpSession;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//@AllArgsConstructor
//@SessionAttributes("bookCart")
//public class CustomerController {
//    private final BookService bookService;
//    private final EbookService ebookService;
//    private final TextbookService textbookService;
//    private final CartService cartService;
//
//    @GetMapping("/catalog")
//    public String findAll(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean isNotAuthorized = authentication == null || authentication.getPrincipal().equals("anonymousUser");
//        model.addAttribute("isAuthorized", !isNotAuthorized);
//        List<Textbook> textBooks = new ArrayList<>(textbookService.getAllTextbooks());
//        List<Ebook> ebooks = new ArrayList<>(ebookService.getAllEbooks());
//        model.addAttribute("textBooks", textBooks);
//        model.addAttribute("ebooks", ebooks);
//        return "customer/customer-book-list";
//    }
//
//    @GetMapping("/cart")
//    public String viewCart(Model model, HttpSession session) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) return "redirect:/login";
//
//        BookCart cart = cartService.getBookCart();
//        model.addAttribute("books", cart.getBooks());
//
//        return "customer/cart";
//    }
//
//    @GetMapping("cart/add-book/{id}")
//    public String addToCart(Model model, @PathVariable("id") Long id) {
//        cartService.addBookToCart(id);
//        return "redirect:/catalog";
//    }
//
//    @GetMapping("cart/remove-book/{id}")
//    public String removeFromCart(Model model, @PathVariable("id") Long id) {
//        cartService.removeBookFromCart(id);
//        return "redirect:/cart";
//    }
//}
//
