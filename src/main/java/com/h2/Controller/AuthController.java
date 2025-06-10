package com.h2.Controller;


import com.h2.Model.*;
import com.h2.Service.AuthService;
import com.h2.Service.ChangeRoleService;
import com.h2.Service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SignInService signInService;
    private final ChangeRoleService changeRoleService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {

        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/auth/signIn")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        return signInService.attemptSignIn(request.getUsername(), request.getEmail(), request.getPassword());
    }

    @PutMapping("/admin/changeRole")
    public ChangeRoleResponse changeRole(@RequestBody @Validated ChangeRoleRequest request) {

        return changeRoleService.changeRole(request.getUsername(), request.getIsAdmin());
    }
}
