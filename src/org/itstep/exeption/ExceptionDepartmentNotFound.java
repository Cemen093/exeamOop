package org.itstep.exeption;

public class ExceptionDepartmentNotFound extends Exception{
    public ExceptionDepartmentNotFound() {
    }

    public ExceptionDepartmentNotFound(String message) {
        super(message);
    }

    public ExceptionDepartmentNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDepartmentNotFound(Throwable cause) {
        super(cause);
    }

    public ExceptionDepartmentNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
