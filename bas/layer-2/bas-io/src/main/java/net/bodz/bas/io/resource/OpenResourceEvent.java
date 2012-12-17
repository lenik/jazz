package net.bodz.bas.io.resource;

import java.util.EventObject;

public class OpenResourceEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    boolean output;
    boolean append;

    public OpenResourceEvent(Object source, boolean output, boolean append) {
        super(source);
        this.output = output;
        this.append = append;
    }

    public boolean isOutput() {
        return output;
    }

    public boolean isAppend() {
        return append;
    }

}
