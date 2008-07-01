package net.bodz.bas.cli;

public class CLIError extends Error {

    private static final long serialVersionUID = -6157786975268203241L;

    public CLIError() {
        super();
    }

    public CLIError(String message, Throwable cause) {
        super(message, cause);
    }

    public CLIError(String message) {
        super(message);
    }

    public CLIError(Throwable cause) {
        super(cause);
    }

}
