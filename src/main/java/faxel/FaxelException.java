package faxel;

public class FaxelException extends RuntimeException {
    public FaxelException(Throwable cause, String message) {
        super(message, cause);
    }

    public FaxelException(Throwable cause, String message, Object ... args) {
        super(String.format(message, args), cause);
    }

    public FaxelException(String message) {
        super(message);
    }

    public FaxelException(String message, Object ... args) {
        super(String.format(message, args));
    }
}
