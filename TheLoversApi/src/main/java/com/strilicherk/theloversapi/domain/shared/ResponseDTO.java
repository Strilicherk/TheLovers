package com.strilicherk.theloversapi.domain.shared;

public record ResponseDTO<T> (Integer status, String message, Boolean success, T data) { }
