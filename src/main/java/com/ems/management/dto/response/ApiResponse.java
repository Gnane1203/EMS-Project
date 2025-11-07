package com.ems.management.dto.response;

import java.time.Instant;

public record ApiResponse<T>(
        String status,
        String message,
        T data,
        Instant timestamp
) {
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, data, Instant.now());
    }

    public static <T> ApiResponse<T> failure(String message, T data) {
        return new ApiResponse<>("FAILURE", message, data, Instant.now());
    }
}
