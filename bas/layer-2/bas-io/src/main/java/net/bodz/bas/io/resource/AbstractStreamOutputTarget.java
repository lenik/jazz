package net.bodz.bas.io.resource;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;

public abstract class AbstractStreamOutputTarget
        extends StreamResourceImplHelper
        implements IStreamOutputTarget {

    private boolean appendMode;

    public AbstractStreamOutputTarget() {
    }

    @Override
    public IStreamOutputTarget clone() {
        try {
            return (IStreamOutputTarget) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isAppendMode() {
        return appendMode;
    }

    @Override
    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    @Override
    public IByteIn _newByteIn()
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ICharIn _newCharIn()
            throws IOException {
        throw new UnsupportedOperationException();
    }

}
