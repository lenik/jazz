package net.bodz.bas.log.objects;

public final class WithThrown {

    private final Object object;
    private final Throwable thrown;

    public WithThrown(Object object, Throwable thrown) {
        if (object == null)
            throw new NullPointerException("object");
        if (thrown == null)
            throw new NullPointerException("thrown");
        this.object = object;
        this.thrown = thrown;
    }

    public Object getObject() {
        return object;
    }

    public Throwable getException() {
        return thrown;
    }

    @Override
    public String toString() {
        return String.format("%s (%s: %s)", //
                object, thrown.getClass().getName(), thrown.getLocalizedMessage());
    }

}
