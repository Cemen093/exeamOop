package org.itstep.exeption;

public class ExceptionEmployeeNotFound extends Exception{
    public ExceptionEmployeeNotFound() {
    }

    public ExceptionEmployeeNotFound(String message) {
        super(message);
    }

    public ExceptionEmployeeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionEmployeeNotFound(Throwable cause) {
        super(cause);
    }

    public ExceptionEmployeeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
