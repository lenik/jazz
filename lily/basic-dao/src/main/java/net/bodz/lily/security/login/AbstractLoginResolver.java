package net.bodz.lily.security.login;

public abstract class AbstractLoginResolver
        implements ILoginResolver {

    protected static Result result() {
        return new Result();
    }

    protected static Result failed(String format, Object... args) {
        String message = String.format(format, args);
        return failed(message);
    }

    protected static Result failed(String message) {
        return new Result().fail(message);
    }

}
