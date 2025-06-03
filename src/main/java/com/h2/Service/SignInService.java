package com.h2.Service;

import com.h2.Entity.User;
import com.h2.Model.SignInResponse;
import com.h2.Repository.UserRepository;
import com.h2.Security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SignInService {
    private final UserRepository userRepository;
    private final JwtIssuer jwtIssuer;
    private final PasswordEncoder passwordEncoder;
    public SignInResponse attemptSignIn(String username, String email, String password) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsAdmin(false);

        User savedUser = userRepository.save(user);

        var token = jwtIssuer.issue(savedUser.getId(), savedUser.getEmail(), Collections.singletonList("ROLE_USER"));

        return SignInResponse.builder()
                .accessToken(token)
                .id(savedUser.getId())
                .username(username)
                .email(email)
                .createdAt(savedUser.getCreatedAt())
                .isAdmin(savedUser.getIsAdmin()).build();
    }
}
