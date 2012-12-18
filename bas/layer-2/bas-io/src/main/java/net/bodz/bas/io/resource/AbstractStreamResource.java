package net.bodz.bas.io.resource;

import net.bodz.bas.err.UnexpectedException;

public abstract class AbstractStreamResource
        extends StreamResourceImplHelper
        implements IStreamResource {

    private boolean appendMode;

    @Override
    public boolean isAppendMode() {
        return appendMode;
    }

    @Override
    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    @Override
    public Long getLength() {
        return null;
    }

    @Override
    public IStreamResource clone() {
        IStreamResource o;
        try {
            o = (AbstractStreamResource) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return o;
    }

}
