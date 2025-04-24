package net.bodz.bas.net.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public class ReadStopSupport {

    Object source;
    List<IReadStoppedListener> readStoppedListeners = new ArrayList<>(1);

    public ReadStopSupport(Object source) {
        this.source = source;
    }

    public void addReadStoppedListener(@NotNull IReadStoppedListener l) {
        readStoppedListeners.add(l);
    }

    public void removeReadStoppedListener(@NotNull IReadStoppedListener l) {
        readStoppedListeners.remove(l);
    }

    public void fireReadStopped(Object value) {
        ReadStopEvent event = new ReadStopEvent(source);
        event.setValue(value);
        fireReadStopped(event);
    }

    public void fireReadStopped(boolean errored) {
        ReadStopEvent event = new ReadStopEvent(source);
        event.setErrored(errored);
        fireReadStopped(event);
    }

    public void fireReadStopped(@NotNull Throwable exception) {
        ReadStopEvent event = new ReadStopEvent(source);
        event.setErrored(true);
        event.setException(exception);
        fireReadStopped(event);
    }

    public void fireReadStopped(@NotNull ReadStopEvent event) {
        for (IReadStoppedListener l : readStoppedListeners)
            l.onReadStopped(event);
    }

}
