package com.example.libraryweb.data.dto;

import com.example.libraryweb.data.modules.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Email should not be empty")

    @Email
    private String email;
}
