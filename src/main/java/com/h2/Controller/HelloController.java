package com.h2.Controller;

import com.h2.Security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring!";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return  "if you see this than you're logged in as user: "
                + principal.getEmail() + " User Id: " + principal.getUserId()
                + " User Role: " + principal.getAuthorities();
    }
}
