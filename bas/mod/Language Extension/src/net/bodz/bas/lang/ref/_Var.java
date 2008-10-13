package net.bodz.bas.lang.ref;

import java.beans.PropertyChangeListener;

public abstract class _Var<T> implements Var<T> {

    @Override
    public void registerChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unregisterChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException();
    }

}
