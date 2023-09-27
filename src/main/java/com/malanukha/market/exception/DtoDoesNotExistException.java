package com.malanukha.market.exception;

public class DtoDoesNotExistException extends RuntimeException {

    public DtoDoesNotExistException(String message) {
        super(message);
    }
}