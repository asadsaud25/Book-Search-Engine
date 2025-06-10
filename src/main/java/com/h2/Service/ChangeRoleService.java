package com.h2.Service;

import com.h2.Entity.User;
import com.h2.Exception.BadRequestException;
import com.h2.Exception.NotFoundException;
import com.h2.Model.ChangeRoleResponse;
import com.h2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChangeRoleService {
    private static final Logger logger = LoggerFactory.getLogger(ChangeRoleService.class);
    private final UserRepository userRepository;

    @Transactional
    public ChangeRoleResponse changeRole(String username, Boolean isAdmin) {
        logger.debug("Attempting to change role for username: {}, isAdmin: {}", username, isAdmin);

        // Input validation
        if (!StringUtils.hasText(username)) {
            logger.warn("Invalid input: username is null or empty");
            throw new BadRequestException("Username cannot be null or empty");
        }
        if (isAdmin == null) {
            logger.warn("Invalid input: isAdmin is null");
            throw new BadRequestException("isAdmin cannot be null");
        }

        try {
            // Find user by username
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> {
                        logger.error("User not found with username: {}", username);
                        return new NotFoundException("User not found with username: " + username);
                    });

            // Check if the role is already set
            if (Objects.equals(user.getIsAdmin(), isAdmin)) {
                String role = isAdmin ? "ADMIN" : "USER";
                logger.info("Role change skipped: user {} already has role {}", username, role);
                throw new BadRequestException("User: " + user.getUsername() + " already has role " + role);
            }

            // Update the user's role
            user.setIsAdmin(isAdmin);
            logger.debug("Updating role for user {} to isAdmin: {}", username, isAdmin);

            // Save the updated user
            User updatedUser;
            try {
                updatedUser = userRepository.save(user);
                logger.info("Successfully changed role for username: {} to isAdmin: {}", username, isAdmin);
            } catch (DataAccessException e) {
                logger.error("Database error while saving user {}: {}", username, e.getMessage(), e);
                throw new RuntimeException("Failed to update user role due to database error: " + e.getMessage(), e);
            }

            // Build and return the response
            return ChangeRoleResponse.builder()
                    .username(updatedUser.getUsername())
                    .isAdmin(updatedUser.getIsAdmin())
                    .message("Role changed successfully")
                    .build();

        } catch (DataAccessException e) {
            logger.error("Database error while retrieving user {}: {}", username, e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve user due to database error: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while changing role for user {}: {}", username, e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while changing user role: " + e.getMessage(), e);
        }
    }
}