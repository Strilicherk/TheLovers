package com.strilicherk.matchapp.controller;

import com.strilicherk.matchapp.domain.shared.ResponseDTO;
import com.strilicherk.matchapp.exceptions.VerificationException;
import com.strilicherk.matchapp.services.PhoneVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/phone-verification")
public class PhoneVerificationController {

    private final PhoneVerificationService phoneVerificationService;

    @Autowired
    public PhoneVerificationController(PhoneVerificationService phoneVerificationService) {
        this.phoneVerificationService = phoneVerificationService;
    }

    @PostMapping("/request")
    public ResponseDTO<Boolean> requestPhoneVerification(@RequestParam String phone, @RequestParam UUID userId) {
        phoneVerificationService.requestPhoneVerification(phone, userId);
        return new ResponseDTO<>(200, "Verification code sent.", Boolean.TRUE);
    }

    @PostMapping("/verify")
    public ResponseDTO<Boolean> verifyPhone(@RequestParam String phone, @RequestParam String verificationCode) {
        return phoneVerificationService.verifyPhone(phone, verificationCode);
    }
}

