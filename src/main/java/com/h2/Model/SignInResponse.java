package com.h2.Model;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class SignInResponse {
    private String accessToken;
    private UUID id;
    private String username;
    private String email;
    private Boolean isAdmin;
    private OffsetDateTime createdAt;
}
