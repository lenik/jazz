package net.bodz.bas.net.util;

import java.util.EventListener;

public interface IReadStoppedListener
        extends EventListener {

    void onReadStopped(ReadStopEvent event);

}
