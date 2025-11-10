package com.ems.management.dto.response;

import java.time.Instant;

public class ApiError {

    private String status;
    private String message;
    private String errorCode;
    private String path;
    private Instant timestamp;

    // 🔹 Constructors
    public ApiError() {
        this.timestamp = Instant.now();
    }

    public ApiError(String status, String message, String errorCode, String path, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
        this.timestamp = timestamp;
    }

    // 🔹 Static factory method (same behavior as record)
    public static ApiError of(String message, String errorCode, String path) {
        return new ApiError("ERROR", message, errorCode, path, Instant.now());
    }

    // 🔹 Getters & Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
