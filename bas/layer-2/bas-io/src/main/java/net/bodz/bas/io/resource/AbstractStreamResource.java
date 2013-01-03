package net.bodz.bas.io.resource;

public abstract class AbstractStreamResource
        extends StreamResourceTemplate
        implements IStreamResource {

    @Override
    public Long getLength() {
        return null;
    }

}
