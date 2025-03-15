package com.example.ChatApp.Controller;

import java.util.Optional;

import com.example.ChatApp.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.ChatApp.Model.AppUser;
import com.example.ChatApp.Repository.UserRepository;
import com.example.ChatApp.Service.Security.JwtUtil;

@RestController
@RequestMapping(name = "/auth")
public class AuthController {
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
	
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<AppUser> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPasswordHash())) {
                String token = jwtUtil.generateToken(user);
                return ResponseEntity.ok(token);
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser newUser) {
        // Check if username or email is already taken
        Optional<AppUser> existingUser = userRepository.findByUsername(newUser.getUsername());
        Optional<AppUser> existingEmail = userRepository.findByEmail(newUser.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        if (existingEmail.isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }

        // Hash the password before saving
        newUser.setPasswordHash(passwordEncoder.encode(newUser.getPasswordHash()));

        // Set default role if not provided
        if (newUser.getRole() == null) {
            newUser.setRole(Role.USER); // Default role: USER
        }

        // Save the user to the database
        AppUser savedUser = userRepository.save(newUser);

        // Generate a JWT token for the new user
        String token = jwtUtil.generateToken(newUser);

        return ResponseEntity.ok(token); // Return JWT token after successful registration
    }
}
