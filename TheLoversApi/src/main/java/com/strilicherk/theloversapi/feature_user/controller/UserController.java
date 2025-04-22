package com.strilicherk.theloversapi.feature_user.controller;

import com.strilicherk.theloversapi.core.domain.model.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.mappers.UserMapper;
import com.strilicherk.theloversapi.feature_user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
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
    public ResponseDTO<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        val result = userService.createUser(userCreateDTO);
        return new ResponseDTO<>(200, "User created.", true, UserMapper.fromUserToUserResponseDto(result));
    }
}
