package com.it.stopwatch.app.exception;

public class ValidationException extends StopwatchException {

    public ValidationException(String message) {
        super(message);
    }
    public ValidationException(String elementLabel, String message) {
        super(String.format(message, elementLabel));
    }

    public ValidationException(String elementLabel, String message, Throwable throwable) {
        super(String.format(message, elementLabel), throwable);
    }
}
