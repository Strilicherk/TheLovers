package com.strilicherk.theloversapi.feature_auth.login.domain.dto;

public record LoginRequestDTO(String phone, String verificationCode, String deviceId) { }