package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ObjectOutputAdapter
        extends AbstractDataOutputAdapter<ObjectOutput>
        implements IObjectOutput {

    private static final long serialVersionUID = 1L;

    public ObjectOutputAdapter(ObjectOutputStream oos) {
        this(oos, oos);
    }

    public <FC extends Flushable & Closeable> ObjectOutputAdapter(ObjectOutput _orig, FC flushClose) {
        super(_orig, flushClose);
    }

    @Override
    public void writeObject(Object obj)
            throws IOException {
        _orig.writeObject(obj);
    }

}
