package com.h2.Service;

import com.h2.Entity.User;
import com.h2.Exception.BadRequestException;
import com.h2.Exception.NotFoundException;
import com.h2.Model.ChangeRoleResponse;
import com.h2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeRoleService {
    private final UserRepository userRepository;

    public ChangeRoleResponse changeRole(String username, Boolean isAdmin) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new NotFoundException("User not found"));
        if (user.getIsAdmin() == isAdmin) {
            var role = user.getIsAdmin() ? "ADMIN":"USER";
            throw new BadRequestException("User: " + user.getUsername() + " already had role " + role);
        }
        user.setIsAdmin(isAdmin);

        User updatedUser = userRepository.save(user);

        return ChangeRoleResponse.builder()
                .username(updatedUser.getUsername())
                .isAdmin(updatedUser.getIsAdmin())
                .message("Role Changed successfully")
                .build();
    }
}
