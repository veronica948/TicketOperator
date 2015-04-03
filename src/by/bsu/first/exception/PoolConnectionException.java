package by.bsu.first.exception;

/**
 * Created by Пользователь on 02.12.2014.
 */
public class PoolConnectionException extends Exception {
    public PoolConnectionException(String message, Throwable e) {
         super(message,e);
    }
}
