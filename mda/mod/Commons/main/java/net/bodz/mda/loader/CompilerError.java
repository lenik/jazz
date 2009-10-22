package net.bodz.mda.loader;

public class CompilerError extends Error {

    private static final long serialVersionUID = 4543476721091439206L;

    public CompilerError() {
        super();
    }

    public CompilerError(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilerError(String message) {
        super(message);
    }

    public CompilerError(Throwable cause) {
        super(cause);
    }

}
