package com.h2.Model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeRoleResponse {
    private String username;
    private Boolean isAdmin;
    private String message;
}
