package net.bodz.bas.las;

import net.bodz.bas.lang.err.IllegalUsageError;

public abstract class _LasUnit implements LasUnit {

    protected final LasUnit prev;

    /**
     * @param prev
     *            <code>null</code> if no prev.
     */
    public _LasUnit(LasUnit prev) {
        this.prev = prev;
    }

    protected abstract boolean _enter(Object... args);

    @Override
    public boolean enter(Object... args) {
        if (prev != null)
            if (!prev.enter(args))
                return false;
        return _enter(args);
    }

    protected abstract void _leave();

    protected <T> T _leave(T returnValue) throws IllegalUsageError {
        _leave();
        return returnValue;
    }

    protected <T extends Throwable> T _leave(T t) throws T {
        _leave();
        return t;
    }

    @Override
    public void leave() throws IllegalUsageError {
        _leave();
        if (prev != null)
            prev.leave();
    }

    @Override
    public <T> T leave(T returnValue) throws IllegalUsageError {
        returnValue = _leave(returnValue);
        if (prev != null)
            returnValue = prev.leave(returnValue);
        return returnValue;
    }

    @Override
    public <T extends Throwable> T leave(T t) throws T {
        t = _leave(t);
        if (prev != null)
            t = prev.leave(t);
        return t;
    }

}
