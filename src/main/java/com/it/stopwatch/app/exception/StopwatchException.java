package com.it.stopwatch.app.exception;

public class StopwatchException extends RuntimeException {

    public StopwatchException() {
    }

    public StopwatchException(String message) {
        super(message);
    }

    public StopwatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public StopwatchException(Throwable cause) {
        super(cause);
    }
}
