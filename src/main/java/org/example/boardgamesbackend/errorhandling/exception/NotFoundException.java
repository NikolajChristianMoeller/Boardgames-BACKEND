package org.example.boardgamesbackend.errorhandling.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("NotFound error: " + message);
    }
}
