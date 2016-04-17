package net.bodz.bas.ui.event;

import net.bodz.bas.meta.decl.EventSource;

public interface IStateChangingSource
        extends EventSource {

    void addStateChangeListener(IStateChangeListener listener);

    void removeStateChangeListener(IStateChangeListener listener);

}
