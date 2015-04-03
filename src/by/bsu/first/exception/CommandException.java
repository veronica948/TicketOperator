package by.bsu.first.exception;

/**
 * Created by Пользователь on 01.12.2014.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
    public CommandException(Throwable e) {
        super(e.getMessage(),e);
    }
    public CommandException(String message,Throwable e) {
        super(message,e);
    }
}
