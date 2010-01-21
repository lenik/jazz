package net.bodz.bas.io.resource.preparation;

import java.io.IOException;

import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

public class StreamWritePreparation
        implements IStreamWritePreparation {

    private final IStreamOutputTarget target;

    private boolean appendMode;
    private boolean autoFlush;

    public StreamWritePreparation(IStreamOutputTarget target) {
        if (target == null)
            throw new NullPointerException("target");
        this.target = target;
    }

    @Override
    public IStreamWritePreparation clone() {
        try {
            IStreamWritePreparation clone = (IStreamWritePreparation) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    public boolean getAppendMode() {
        return appendMode;
    }

    public StreamWritePreparation setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
        return this;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public StreamWritePreparation setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
        return this;
    }

    public void writeString(String string)
            throws IOException {
        ICharOut out = target.newCharOut();
        try {
            out.write(string);
        } finally {
            out.close();
        }
    }

    public void writeBytes(byte[] bytes, int off, int len)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        IByteOut out = target.newByteOut();
        try {
            out.write(bytes, off, len);
        } finally {
            out.close();
        }
    }

    public void writeBytes(byte[] bytes)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        writeBytes(bytes, 0, bytes.length);
    }

}
