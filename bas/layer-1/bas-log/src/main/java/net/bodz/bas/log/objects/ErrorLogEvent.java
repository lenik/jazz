package net.bodz.bas.log.objects;

public final class ErrorLogEvent
        extends AbstractLogEvent {

    private final Object object;
    private final Throwable exception;

    public ErrorLogEvent(Object object, Throwable exception) {
        if (object == null)
            throw new NullPointerException("object");
        if (exception == null)
            throw new NullPointerException("thrown");
        this.object = object;
        this.exception = exception;
    }

    @Override
    public Object getMessage() {
        return object;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public String toString() {
        return String.format("%s (%s: %s)", //
                object, exception.getClass().getName(), exception.getLocalizedMessage());
    }

}
