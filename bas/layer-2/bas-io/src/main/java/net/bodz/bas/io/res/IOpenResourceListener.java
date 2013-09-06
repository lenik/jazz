package net.bodz.bas.io.res;

import java.io.IOException;
import java.util.EventListener;

public interface IOpenResourceListener
        extends EventListener {

    void openResource(OpenResourceEvent event)
            throws IOException;

}
