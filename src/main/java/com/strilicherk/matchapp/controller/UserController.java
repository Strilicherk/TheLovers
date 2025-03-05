package com.strilicherk.matchapp.controller;

import com.strilicherk.matchapp.domain.shared.ResponseDTO;
import com.strilicherk.matchapp.domain.user.User;
import com.strilicherk.matchapp.domain.user.UserCreateDTO;
import com.strilicherk.matchapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser() {
        return "Hello World";
    }

    @PostMapping("teste")
    public String postTest(@RequestBody String data) {
        return data;
    }

    @PostMapping("create-user")
    public ResponseDTO<String> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        userService.createUser(userCreateDTO);
        return new ResponseDTO<>(200, "User created.", userCreateDTO.phone());
    }

}
