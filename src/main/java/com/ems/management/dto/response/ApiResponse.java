package com.ems.management.dto.response;

import java.time.Instant;

public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
    private Instant timestamp;

    // 🔹 Constructors
    public ApiResponse() {
        this.timestamp = Instant.now();
    }

    public ApiResponse(String status, String message, T data, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    // 🔹 Static factory methods (same as record)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, data, Instant.now());
    }

    public static <T> ApiResponse<T> failure(String message, T data) {
        return new ApiResponse<>("FAILURE", message, data, Instant.now());
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
