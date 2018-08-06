package gbs.exception;

import java.io.FileNotFoundException;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
