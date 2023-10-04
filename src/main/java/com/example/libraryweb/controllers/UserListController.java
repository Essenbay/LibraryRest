//package com.example.libraryweb.controllers;
//
//import com.example.libraryweb.data.dto.UserDto;
//import com.example.libraryweb.data.modules.Book;
//import com.example.libraryweb.data.modules.User;
//import com.example.libraryweb.services.*;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@AllArgsConstructor
//@Controller
//public class UserListController {
//    private final UserService userService;
//
//    @GetMapping("/users")
//    public String listRegisteredUsers(Model model) {
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "admin/user-list";
//    }
//
//    @GetMapping("/users/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/users";
//    }
//}
//
