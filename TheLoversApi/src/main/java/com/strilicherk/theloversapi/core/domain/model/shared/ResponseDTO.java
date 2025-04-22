package com.strilicherk.theloversapi.core.domain.model.shared;

public record ResponseDTO<T> (Integer status, String message, Boolean success, T data) { }
