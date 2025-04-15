package com.strilicherk.theloversapi.feature_auth.login.controller;

import com.strilicherk.theloversapi.feature_auth.login.services.LoginServiceImpl;
import com.strilicherk.theloversapi.feature_auth.login.domain.dto.LoginRequestDTO;
import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_auth.security.TokenGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Login", description = "Endpoints for JWT authentication and refresh tokens.")
public class LoginController {
    private final LoginServiceImpl loginServiceImpl;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public LoginController(LoginServiceImpl loginServiceImpl, TokenGenerator tokenGenerator) {
        this.loginServiceImpl = loginServiceImpl;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Authenticate user",
            description = "Authenticates the user using phone number and verification code. Returns a JWT and Refresh token."
    )
    public ResponseDTO<Map<String, String>> userLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginServiceImpl.login(loginRequestDTO);
    }

//    @GetMapping("/login/adm")
//    @Operation(summary = "Adm login", description = "Generate adm bearer token")
//    public ResponseDTO<String> admLogin() {
//        return new ResponseDTO<>(200, "Auth token", true, tokenGenerator.generateAdminToken());
//    }

//    @PostMapping("/verify")
//    @Operation(
//            summary = "Validate code.",
//            description = "Compare the six digits code with the phone number to validate user."
//    )
//    public ResponseDTO<String> verifyPhone(@RequestParam String phone, @RequestParam String verificationCode) {
//        return phoneVerificationService.verifyPhone(phone, verificationCode);
//    }
}
