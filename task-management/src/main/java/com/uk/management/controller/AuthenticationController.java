package com.uk.management.controller;

import com.uk.management.dto.AuthRequest;
import com.uk.management.dto.AuthResponse;
import com.uk.management.model.User;
import com.uk.management.service.JWTService;
import com.uk.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        final String jwt = jwtService.createJwtToken(authenticationRequest);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        userService.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }
}
