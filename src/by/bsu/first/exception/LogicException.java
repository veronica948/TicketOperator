package by.bsu.first.exception;

/**
 * Created by Пользователь on 30.11.2014.
 */
public class LogicException extends Exception {
    public LogicException(String message) {
        super(message);
    }
    public LogicException(Throwable e) {
        super(e.getMessage(), e);
    }
    public LogicException(String message,Throwable e) {
        super(message,e);
}
}
