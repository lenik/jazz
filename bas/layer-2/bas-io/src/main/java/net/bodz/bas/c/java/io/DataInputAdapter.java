package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;

public class DataInputAdapter
        extends AbstractDataInputAdapter<DataInput> {

    private static final long serialVersionUID = 1L;

    public DataInputAdapter(DataInputStream dis) {
        this(dis, dis);
    }

    public DataInputAdapter(DataInput dataInput, Closeable closable) {
        super(dataInput, closable);
    }

}
