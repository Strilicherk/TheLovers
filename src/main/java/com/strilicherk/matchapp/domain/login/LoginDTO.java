package com.strilicherk.matchapp.domain.login;

public record LoginDTO(String phone, String verificationCode, String deviceId) { }