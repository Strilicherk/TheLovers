package com.strilicherk.theloversapi.feature_auth.domain.sms.dto;

public record PhoneVerificationRequestDTO(
        String phone,
        String smsCode,
        String deviceId
) { }