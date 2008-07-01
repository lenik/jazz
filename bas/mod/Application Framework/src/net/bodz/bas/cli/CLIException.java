package net.bodz.bas.cli;

public class CLIException extends Exception {

    private static final long serialVersionUID = -7537357433011384587L;

    public CLIException() {
        super();
    }

    public CLIException(String message, Throwable cause) {
        super(message, cause);
    }

    public CLIException(String message) {
        super(message);
    }

    public CLIException(Throwable cause) {
        super(cause);
    }

}
