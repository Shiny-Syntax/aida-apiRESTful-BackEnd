package com.shinysyntax.aida.aida.exception;

public class RedirectException extends RuntimeException {
    private final String location;

    public RedirectException(String message, String location) {
        super(message);
        this.location = location;
    }

    public String getLocation() { return location; }
}
