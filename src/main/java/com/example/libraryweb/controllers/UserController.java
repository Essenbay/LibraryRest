
package com.example.libraryweb.controllers;

import com.example.libraryweb.data.dto.*;
import com.example.libraryweb.data.modules.User;
import com.example.libraryweb.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getProfile() {
        Optional<User> currentUser = userService.getCurrentUser();
        if (currentUser.isPresent()) {
            User user = currentUser.get();
            return ResponseEntity.ok(new UserDto(user.getId(), user.getName(), user.getEmail()));
        } else {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);

        }
    }
}