package net.bodz.bas.fs;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;

public class DefaultDumpToolkit
        implements IDumpToolkit {

    private final IWriteToolkit wt;

    public DefaultDumpToolkit(IWriteToolkit writeToolkit) {
        if (writeToolkit == null)
            throw new NullPointerException("writeToolkit");
        this.wt = writeToolkit;
    }

    @Override
    public void dumpObject(Object o)
            throws IOException {
        OutputStream out = wt.newOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        try {
            objOut.writeObject(o);
        } finally {
            objOut.close();
        }
    }

    @Override
    public void dumpObjects(Collection<Object> objects)
            throws IOException {
        OutputStream out = wt.newOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        try {
            for (Object o : objects)
                objOut.writeObject(o);
        } finally {
            objOut.close();
        }
    }

    @Override
    public void dumpXML(Object o)
            throws IOException {
        OutputStream out = wt.newOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        // encoder.setExceptionListener(exceptionBuffer);
        try {
            encoder.writeObject(o);
        } finally {
            encoder.close();
        }
    }

}
