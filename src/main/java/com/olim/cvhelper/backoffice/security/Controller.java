package com.olim.cvhelper.backoffice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;
    @Value("${admin.pass}")
    private String adminPass;


    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Credentials credentials, @RequestHeader("Auth") String pass) {
        if (pass.equals(adminPass)) {
            userService.createUser(credentials);
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }
}
