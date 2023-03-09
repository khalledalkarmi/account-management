package exception;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException() {
    }

    public InvalidAccountException(String s) {
        super(s);
    }

    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
