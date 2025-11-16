package com.shinysyntax.aida.aida.exception;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private final LocalDateTime timestamp;
    private int statusCode;
    private String path;
    private String message;
    private String error;
    private Map<String, ?> details;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // ------------ CONSTRUTORES ESTÁTICOS PRONTOS ------------
    
    public static ApiResponse success(int statusCode, String message, String path) {
        ApiResponse resp = new ApiResponse();
        resp.setStatusCode(statusCode);
        resp.setMessage(message);
        resp.setPath(path);
        return resp;
    }

    public static ApiResponse error(int statusCode, String error, String message, String path, Map<String, ?> details) {
        ApiResponse resp = new ApiResponse();
        resp.setStatusCode(statusCode);
        resp.setError(error);
        resp.setMessage(message);
        resp.setPath(path);
        resp.setDetails(details);
        return resp;
    }

    // ------------ GETTERS & SETTERS ------------

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, ?> getDetails() {
        return details;
    }

    public void setDetails(Map<String, ?> details) {
        this.details = details;
    }
}
