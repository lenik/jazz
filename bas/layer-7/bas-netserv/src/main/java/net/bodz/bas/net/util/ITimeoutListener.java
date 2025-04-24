package net.bodz.bas.net.util;

import java.util.EventListener;

public interface ITimeoutListener
        extends EventListener {

    void onTimeout(TimeoutEvent event);

}
