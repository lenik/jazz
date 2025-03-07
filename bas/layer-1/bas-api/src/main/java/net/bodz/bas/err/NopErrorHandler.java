package net.bodz.bas.err;

public class NopErrorHandler<E, T>
        implements IErrorHandler<E, T> {

    private final boolean state;

    public NopErrorHandler(boolean state) {
        this.state = state;
    }

    @Override
    public boolean handleError(E error, T source) {
        return state;
    }

}
