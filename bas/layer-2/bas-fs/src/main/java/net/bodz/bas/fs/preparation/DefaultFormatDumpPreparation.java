package net.bodz.bas.fs.preparation;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;

public class DefaultFormatDumpPreparation
        implements IFormatDumpPreparation {

    private final IStreamWritePreparation writePrep;

    public DefaultFormatDumpPreparation(IStreamWritePreparation streamWritePreparation) {
        if (streamWritePreparation == null)
            throw new NullPointerException("streamWritePreparation");
        this.writePrep = streamWritePreparation;
    }

    @Override
    public void dumpObject(Object o)
            throws IOException {
        OutputStream out = writePrep.newOutputStream();
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
        OutputStream out = writePrep.newOutputStream();
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
        OutputStream out = writePrep.newOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        // encoder.setExceptionListener(exceptionBuffer);
        try {
            encoder.writeObject(o);
        } finally {
            encoder.close();
        }
    }

}
