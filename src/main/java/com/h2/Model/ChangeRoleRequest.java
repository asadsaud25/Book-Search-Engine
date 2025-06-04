package com.h2.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRoleRequest {
    private String username;
    private Boolean isAdmin;

}
