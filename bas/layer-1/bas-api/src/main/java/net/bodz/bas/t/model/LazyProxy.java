package net.bodz.bas.t.model;

public abstract class LazyProxy<T>
        extends AbstractProxy<T> {

    @Override
    public T getWrapped() {
        if (_orig == null) {
            synchronized (this) {
                if (_orig == null) {
                    _orig = createInstance();
                }
            }
        }
        return _orig;
    }

    protected abstract T createInstance();

}
