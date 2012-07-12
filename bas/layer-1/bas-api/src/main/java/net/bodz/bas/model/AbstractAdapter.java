package net.bodz.bas.model;

/**
 * Adapter Pattern:
 * 
 * Adapter is used when you have an abstract interface, and you want to map that interface to
 * another object which has similar functional role, but a different interface.
 * 
 * @param S
 *            The original interface which will be mapped to another interface.
 */
public abstract class AbstractAdapter<S>
        implements IWrapper<S> {

    protected final S _orig;

    public AbstractAdapter(S _orig) {
        if (_orig == null)
            throw new NullPointerException("_orig");
        this._orig = _orig;
    }

    @Override
    public S getWrapped() {
        return _orig;
    }

}
