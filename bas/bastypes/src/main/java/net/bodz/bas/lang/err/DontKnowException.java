package net.bodz.bas.lang.err;

public class DontKnowException extends RuntimeException {

    private static final long serialVersionUID = 3241400062992357227L;

    private Object            guessValue;

    public DontKnowException() {
        super();
    }

    public DontKnowException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontKnowException(String message) {
        super(message);
    }

    public DontKnowException(Throwable cause) {
        super(cause);
    }

    public DontKnowException(Object guessValue) {
        super();
        this.guessValue = guessValue;
    }

    public DontKnowException(Object guessValue, String message, Throwable cause) {
        super(message, cause);
        this.guessValue = guessValue;
    }

    public DontKnowException(Object guessValue, String message) {
        super(message);
        this.guessValue = guessValue;
    }

    public DontKnowException(Object guessValue, Throwable cause) {
        super(cause);
        this.guessValue = guessValue;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (guessValue != null)
            message += " (guess: " + guessValue + ")";
        return message;
    }

    public Object getGuessValue() {
        return guessValue;
    }

}
