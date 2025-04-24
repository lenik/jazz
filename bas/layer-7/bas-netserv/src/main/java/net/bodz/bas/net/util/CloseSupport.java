package net.bodz.bas.net.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public class CloseSupport {

    Object source;
    List<IClosedListener> closeListeners = new ArrayList<>(1);

    public CloseSupport(Object source) {
        this.source = source;
    }

    public void addCloseListener(@NotNull IClosedListener l) {
        closeListeners.add(l);
    }

    public void removeCloseListener(@NotNull IClosedListener l) {
        closeListeners.remove(l);
    }

    public void fireClose(Object value) {
        CloseEvent event = new CloseEvent(source);
        event.setValue(value);
        fireClose(event);
    }

    public void fireClose(boolean errored) {
        CloseEvent event = new CloseEvent(source);
        event.setErrored(errored);
        fireClose(event);
    }

    public void fireClose(@NotNull Throwable exception) {
        CloseEvent event = new CloseEvent(source);
        event.setErrored(true);
        event.setException(exception);
        fireClose(event);
    }

    public void fireClose(@NotNull CloseEvent event) {
        for (IClosedListener l : closeListeners)
            l.onClosed(event);
    }

}
