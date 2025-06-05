package com.bni.bni.service;

import com.bni.bni.entity.User;
import com.bni.bni.repository.UserRepository;
import com.bni.bni.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(String username, String email, String password) {
        if (repo.existsByUsername(username)) {
            return "User already exists";
        }
    
        User user = new User();
        user.setUsername(username);
        user.setEmailAddress(email);
        user.setPassword(encoder.encode(password));
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());
        user.setIsActive(true); // default true
        repo.save(user);
    
        return "Registered successfully";
    }
    

    public String login(String username, String password) {
        Optional<User> user = repo.findByUsername(username);
        if (user.isPresent() && encoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(username, "USER"); // role hardcoded karena tidak ada di DB
        }

        return null;
    }
}
