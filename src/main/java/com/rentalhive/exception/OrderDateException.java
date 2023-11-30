package com.rentalhive.exception;

import lombok.Getter;

@Getter
public class OrderDateException extends Exception {

    private final String field;

    public OrderDateException(String message, String field) {
        super(message);
        this.field = field;
    }
}
