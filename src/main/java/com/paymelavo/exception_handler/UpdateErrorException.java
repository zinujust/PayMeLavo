package com.paymelavo.exception_handler;

public class UpdateErrorException extends RuntimeException{
    public UpdateErrorException(String message) {
        super(message);
    }
}
