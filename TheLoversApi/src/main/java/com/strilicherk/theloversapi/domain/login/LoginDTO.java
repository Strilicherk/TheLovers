package com.strilicherk.theloversapi.domain.login;

public record LoginDTO(String phone, String verificationCode, String deviceId) { }