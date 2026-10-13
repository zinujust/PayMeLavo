package com.paymelavo.exception_handler;

public class DataNotMatchingException extends RuntimeException{
    public DataNotMatchingException(String message) {
        super(message);
    }
}
