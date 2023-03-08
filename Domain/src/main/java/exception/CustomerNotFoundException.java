package exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message) {
        super("Customer not found");
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
