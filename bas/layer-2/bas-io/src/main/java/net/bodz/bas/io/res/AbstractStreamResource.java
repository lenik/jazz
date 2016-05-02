package net.bodz.bas.io.res;

import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.io.res.tools.StreamWriting;

public abstract class AbstractStreamResource
        extends StreamResourceTemplate
        implements IStreamResource {

    public StreamReading read() {
        return new StreamReading(this);
    }

    public StreamWriting write() {
        return new StreamWriting(this);
    }

}
