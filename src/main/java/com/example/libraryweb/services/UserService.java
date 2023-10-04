package com.example.libraryweb.services;


import com.example.libraryweb.data.dto.RegistrationUserDto;
import com.example.libraryweb.data.dto.UserDto;
import com.example.libraryweb.data.modules.Role;
import com.example.libraryweb.data.modules.User;
import com.example.libraryweb.data.repositories.RoleRepository;
import com.example.libraryweb.data.repositories.UserRepository;
import com.example.libraryweb.util.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public User saveUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setName(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_CUSTOMER").get();
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

    public void removeAdminRole(User user) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        roles.remove(roleRepository.findByName("ROLE_ADMIN").get());
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void giveUserAdminRole(User user) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        roles.add(roleRepository.findByName("ROLE_ADMIN").get());
        user.setRoles(roles);
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Book not found");
        }
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userRepository.findByEmail(username);
        } else {
            return Optional.empty();
        }
    }

    public void updateUser(User user) {
        User currentUser = getCurrentUser().get();
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        if (!user.getPassword().isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(currentUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
}