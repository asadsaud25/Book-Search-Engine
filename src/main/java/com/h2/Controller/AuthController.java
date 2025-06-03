package com.h2.Controller;


import com.h2.Model.LoginRequest;
import com.h2.Model.LoginResponse;
import com.h2.Model.SignInRequest;
import com.h2.Model.SignInResponse;
import com.h2.Service.AuthService;
import com.h2.Service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final SignInService signInService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {

        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/signIn")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        return signInService.attemptSignIn(request.getUsername(), request.getEmail(), request.getPassword());
    }
}
