package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.Flushable;

public class DataOutputAdapter
        extends AbstractDataOutputAdapter<DataOutput> {

    private static final long serialVersionUID = 1L;

    public DataOutputAdapter(DataOutputStream dos) {
        this(dos, dos);
    }

    public <FC extends Flushable & Closeable> DataOutputAdapter(DataOutput _orig, FC flushClose) {
        super(_orig, flushClose);
    }

}
