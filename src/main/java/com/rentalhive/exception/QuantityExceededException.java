package com.rentalhive.exception;

public class QuantityExceededException extends Exception{

    public QuantityExceededException(String message) {
        super(message);
    }
}
