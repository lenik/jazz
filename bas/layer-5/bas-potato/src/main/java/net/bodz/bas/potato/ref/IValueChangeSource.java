package net.bodz.bas.potato.ref;

import net.bodz.bas.meta.decl.EventSource;

public interface IValueChangeSource
        extends EventSource {

    void addValueChangeListener(IValueChangeListener listener);

    void removeValueChangeListener(IValueChangeListener listener);

}
