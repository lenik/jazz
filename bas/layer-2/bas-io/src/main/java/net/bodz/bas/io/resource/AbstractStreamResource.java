package net.bodz.bas.io.resource;

public abstract class AbstractStreamResource
        extends StreamResourceImplHelper
        implements IStreamResource {

    @Override
    public Long getLength() {
        return null;
    }

}
