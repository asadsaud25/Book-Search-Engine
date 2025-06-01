package com.h2.Service;

import com.h2.Entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final String EXISTING_EMAIL = "test@test.com";
    public Optional<UserEntity> findByEmail(String email) {
        // TODO: Add Database Here!!
        if (!EXISTING_EMAIL.equalsIgnoreCase(email)) return Optional.empty();

        var user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@test.com");
        user.setPassword("$2a$12$ccRvwU2hn/jIYXdpDJxZb.OyD.s.XVQdjEd/8aa0JLSWlFs9xFXNK"); //test
        user.setRole("ROLE_ADMIN");
        user.setExtraInfo("My nice admin");
        return Optional.of(user);
    }
}
