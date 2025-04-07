package com.strilicherk.theloversapi.controller;

import com.strilicherk.theloversapi.domain.login.LoginDTO;
import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.security.TokenGenerator;
import com.strilicherk.theloversapi.services.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Login", description = "Endpoints for JWT authentication and refresh tokens.")
public class LoginController {
    private final LoginService loginService;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public LoginController(LoginService loginService, TokenGenerator tokenGenerator) {
        this.loginService = loginService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Authenticate user",
            description = "Authenticates the user using phone number and verification code. Returns a JWT and Refresh token."
    )
    public ResponseDTO<Map<String, String>> userLogin(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

    @GetMapping("/login/adm")
    @Operation(
            summary = "Adm login",
            description = "Generate adm bearer token"
    )
    public ResponseDTO<String> admLogin() {
        return new ResponseDTO<>(200, "Auth token", true, tokenGenerator.generateAdminToken());
    }
}
