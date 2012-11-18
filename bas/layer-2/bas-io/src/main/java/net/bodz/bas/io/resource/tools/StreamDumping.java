package net.bodz.bas.io.resource.tools;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.util.Collection;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamOutputTargetWrapper;

public class StreamDumping
        implements IStreamDumping {

    private final IStreamOutputTarget target;

    public StreamDumping(IStreamOutputTargetWrapper wrapper) {
        this(wrapper.getOutputTarget());
    }

    public StreamDumping(IStreamOutputTarget target) {
        if (target == null)
            throw new NullPointerException("target");
        this.target = target;
    }

    @Override
    public IStreamDumping clone() {
        try {
            return (IStreamDumping) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public void dumpObject(Object o)
            throws IOException {
        ObjectOutput out = target.newObjectOutput();
        try {
            out.writeObject(o);
        } finally {
            out.close();
        }
    }

    @Override
    public void dumpObjects(Collection<?> objects)
            throws IOException {
        ObjectOutput out = target.newObjectOutput();
        try {
            for (Object o : objects)
                out.writeObject(o);
        } finally {
            out.close();
        }
    }

    @Override
    public void dumpXML(Object o)
            throws IOException {
        OutputStream out = target.newOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        // encoder.setExceptionListener(exceptionBuffer);
        try {
            encoder.writeObject(o);
        } finally {
            encoder.close();
        }
    }

}
