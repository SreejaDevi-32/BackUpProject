package com.virtusa.telecom.utility.commonlib;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private T data;

    public ApiResponse(T data) {
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public ApiResponse(LocalDateTime timestamp, T data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }
}
