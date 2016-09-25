package ru.ok.rmatafonov.exception;

public class NotSupportedDriverException extends RuntimeException {

    public NotSupportedDriverException() {
        super();
    }

    public NotSupportedDriverException(String message) {
        super(message);
    }

    public NotSupportedDriverException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
