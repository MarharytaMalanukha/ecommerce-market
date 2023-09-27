package com.malanukha.market.exception;

public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}