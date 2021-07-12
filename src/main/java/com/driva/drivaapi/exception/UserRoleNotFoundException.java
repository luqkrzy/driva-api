package com.driva.drivaapi.exception;

public class UserRoleNotFoundException extends RuntimeException {


    public UserRoleNotFoundException() {
        super();
    }

    public UserRoleNotFoundException(String message) {
        super(message);
    }

    public UserRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserRoleNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
