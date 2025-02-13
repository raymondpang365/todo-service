package com.raymondpang365.config.exception;

public class DynamicUnauthorized extends DynamicException {

    public DynamicUnauthorized(String message, String code) {
        super(message, code);
    }

    public DynamicUnauthorized(String message) {
        super(message, "UNAUTHORIZED");
    }
}
