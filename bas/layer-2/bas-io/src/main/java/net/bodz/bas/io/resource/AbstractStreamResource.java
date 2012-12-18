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
    public AbstractStreamResource clone() {
        try {
            AbstractStreamResource o = (AbstractStreamResource) super.clone();
            return o;
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
