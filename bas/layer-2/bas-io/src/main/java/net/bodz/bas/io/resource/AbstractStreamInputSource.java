package net.bodz.bas.io.resource;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

public abstract class AbstractStreamInputSource
        extends StreamResourceImplHelper
        implements IStreamInputSource {

    public AbstractStreamInputSource() {
    }

    @Override
    public IStreamInputSource clone() {
        try {
            return (AbstractStreamInputSource) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public IByteOut _newByteOut(boolean append)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ICharOut _newCharOut(boolean append)
            throws IOException {
        throw new UnsupportedOperationException();
    }

}
