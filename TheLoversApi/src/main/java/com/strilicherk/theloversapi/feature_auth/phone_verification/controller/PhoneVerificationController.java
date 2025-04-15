package com.strilicherk.theloversapi.feature_auth.phone_verification.controller;

import com.strilicherk.theloversapi.feature_auth.phone_verification.services.PhoneVerificationService;
import com.strilicherk.theloversapi.feature_auth.phone_verification.domain.PhoneVerificationRequestDTO;
import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/phone-verification")
@Tag(name = "PhoneVerification", description = "Endpoint to send an SMS code to validate a phone number.")
public class PhoneVerificationController {
    private final PhoneVerificationService phoneVerificationService;

    @Autowired
    public PhoneVerificationController(PhoneVerificationService phoneVerificationService) {
        this.phoneVerificationService = phoneVerificationService;
    }

    @PostMapping("/request")
    @Operation(
            summary = "Send sms code.",
            description = "Send a six digit code to the number to validate."
    )
    public ResponseDTO<Boolean> requestPhoneVerification(@RequestBody PhoneVerificationRequestDTO dto) {
        log.info("Received request to send code to phone number {}", dto);
        return phoneVerificationService.requestPhoneVerification(dto.phone());
    }
}

