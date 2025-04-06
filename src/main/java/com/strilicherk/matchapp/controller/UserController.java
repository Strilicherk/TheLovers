package com.strilicherk.matchapp.controller;

import com.strilicherk.matchapp.domain.shared.ResponseDTO;
import com.strilicherk.matchapp.domain.user.UserCreateDTO;
import com.strilicherk.matchapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Tag(name = "User", description = "Endpoints for user operations.")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    @Operation(summary = "Create user.", description = "Create an user, only phone number necessary.")
    public ResponseDTO<String> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }
}
