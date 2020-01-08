package com.calculator.exercise.exceptionhandling;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CustomErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private List<String> details;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(String error, String details) {
        this(error, Arrays.asList(details));
    }

    public CustomErrorResponse(String error, List<String> details) {
        timestamp = LocalDateTime.now();
        this.error = error;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
