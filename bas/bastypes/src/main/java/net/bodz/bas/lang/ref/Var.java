package net.bodz.bas.lang.ref;

import java.beans.PropertyChangeListener;

public interface Var<T> extends Ref<T> {

    VarMeta getMeta();

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

}
