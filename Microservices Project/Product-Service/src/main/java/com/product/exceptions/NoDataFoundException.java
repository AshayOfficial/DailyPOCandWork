package com.product.exceptions;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
