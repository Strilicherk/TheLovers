package com.strilicherk.theloversapi.feature_auth.controller;

import com.strilicherk.theloversapi.feature_auth.domain.session.ValidateSessionRequestDTO;
import com.strilicherk.theloversapi.feature_auth.domain.session.ValidateSessionResponseDTO;
import com.strilicherk.theloversapi.feature_auth.services.AuthenticationService;
import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.PhoneVerificationResponseDTO;
import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.PhoneVerificationRequestDTO;
import com.strilicherk.theloversapi.core.domain.model.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.SendSmsCodeRequestDTO;
import com.strilicherk.theloversapi.feature_auth.services.session.ValidateSessionService;
import com.strilicherk.theloversapi.feature_auth.services.sms.SendSmsCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "Login", description = "Endpoints for JWT authentication and refresh jwt.")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final SendSmsCodeService sendSmsCodeService;
    private final ValidateSessionService validateSessionService;

    @Autowired
    public AuthController(AuthenticationService authenticationService,
                          SendSmsCodeService sendSmsCodeService,
                          ValidateSessionService validateSessionService) {
        this.authenticationService = authenticationService;
        this.sendSmsCodeService = sendSmsCodeService;
        this.validateSessionService = validateSessionService;
    }

    @PostMapping("/request-code")
    @Operation(
            summary = "Request phone verification code",
            description = "Sends a six-digit verification code via SMS to the specified phone number."
    )
    public ResponseDTO<Boolean> requestVerificationCode(@RequestBody SendSmsCodeRequestDTO dto) {
        log.info("Received request to send code to phone number {}", dto);
        val result = sendSmsCodeService.sendSmsCode(dto.phone());
        return new ResponseDTO<>(200, "Sms code sent.", true, result);
    }

    @PostMapping("/verify-code")
    @Operation(
            summary = "Verify code and authenticate user",
            description = "Validates the verification code and returns authentication tokens."
    )
    public ResponseDTO<PhoneVerificationResponseDTO> verifyCode(@RequestBody PhoneVerificationRequestDTO dto) {
        log.info("Verifying code for phone: {}", dto);
        val result = authenticationService.authenticate(dto);
        return new ResponseDTO<>(200, "User authenticated.", true, result);
    }

    @PostMapping("/validate-session")
    @Operation(
            summary = "Validate session",
            description = "Validates refresh and JWT tokens and returns new ones if valid."
    )
    public ResponseDTO<ValidateSessionResponseDTO> validateSession(@RequestBody ValidateSessionRequestDTO dto) {
        log.info("Validating session with tokens: {}", dto);
        val result = validateSessionService.validateSession(dto);
        if (!result.isRefreshTokenValid()) {
            return new ResponseDTO<>(200, "Session is not valid.", false, result);
        }
        return new ResponseDTO<>(200, "Session is valid.", true, result);
    }
}
