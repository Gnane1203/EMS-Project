package com.ems.management.dto.response;

import java.time.Instant;

public record ApiError(
        String status,
        String message,
        String errorCode,
        String path,
        Instant timestamp
) {
    public static ApiError of(String message, String errorCode, String path) {
        return new ApiError("ERROR", message, errorCode, path, Instant.now());
    }
}

