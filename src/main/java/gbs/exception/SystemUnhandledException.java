package gbs.exception;

import java.util.concurrent.ExecutionException;

public class SystemUnhandledException extends RuntimeException {
    public SystemUnhandledException(String message, Throwable cause) {
        super(message, cause);
    }
}
