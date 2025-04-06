package com.strilicherk.matchapp.controller;

import com.strilicherk.matchapp.domain.shared.ResponseDTO;
import com.strilicherk.matchapp.services.login.PhoneVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/phone-verification")
@Tag(name = "PhoneVerification", description = "Endpoints for send and validate phone numbers with SMS code.")
public class PhoneVerificationController {

    private final PhoneVerificationService phoneVerificationService;

    @Autowired
    public PhoneVerificationController(PhoneVerificationService phoneVerificationService) {
        this.phoneVerificationService = phoneVerificationService;
    }

    @PostMapping("/request")
    @Operation(
            summary = "Send code.",
            description = "Send a six digit code to the number to validate."
    )
    public ResponseDTO<Boolean> requestPhoneVerification(@RequestParam String phone, @RequestParam UUID userId) {
        phoneVerificationService.requestPhoneVerification(phone, userId);
        return new ResponseDTO<>(200, "Verification code sent.",true, true);
    }

    @PostMapping("/verify")
    @Operation(
            summary = "Validate code.",
            description = "Compare the six digits code with the phone number to validate user."
    )
    public ResponseDTO<String> verifyPhone(@RequestParam String phone, @RequestParam String verificationCode) {
        return phoneVerificationService.verifyPhone(phone, verificationCode);
    }
}

