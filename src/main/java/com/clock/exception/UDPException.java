package com.clock.exception;

/**
 * Created by qinjun on 2016/3/4.
 */
public class UDPException extends RuntimeException {

    public UDPException() {
        super();
    }

    public UDPException(String message) {
        super(message);
    }

    public UDPException(String message, Throwable cause) {
        super(message, cause);
    }

    public UDPException(Throwable cause) {
        super(cause);
    }

    protected UDPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
