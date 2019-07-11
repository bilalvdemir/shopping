package com.example.shopping.shopping.exception;

public class BadRequestException extends Exception {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String exceptionString) {
        super(exceptionString);
    }
}
