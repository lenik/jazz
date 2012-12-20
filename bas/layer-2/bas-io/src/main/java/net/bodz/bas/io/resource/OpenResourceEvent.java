package net.bodz.bas.io.resource;

import java.nio.file.OpenOption;
import java.util.EventObject;

public class OpenResourceEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    boolean output;
    OpenOption[] options;

    public OpenResourceEvent(Object source, boolean output, OpenOption... options) {
        super(source);
        this.output = output;
        this.options = options;
    }

    public boolean isOutput() {
        return output;
    }

    public OpenOption[] getOptions() {
        return options;
    }

}
