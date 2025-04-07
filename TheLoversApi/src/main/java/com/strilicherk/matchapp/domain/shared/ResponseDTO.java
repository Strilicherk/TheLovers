package com.strilicherk.matchapp.domain.shared;

public record ResponseDTO<T> (Integer status, String message, T data) { }
