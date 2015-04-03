package by.bsu.first.exception;

/**
 * Created by Пользователь on 17.01.2015.
 */
public class HashingException extends Exception {
    public HashingException(String message) {
        super(message);
    }

    public HashingException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashingException(Throwable cause) {
        super(cause);
    }

    public HashingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
