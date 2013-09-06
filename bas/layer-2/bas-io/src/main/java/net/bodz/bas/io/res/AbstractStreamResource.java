package net.bodz.bas.io.res;

public abstract class AbstractStreamResource
        extends StreamResourceTemplate
        implements IStreamResource {

    @Override
    public Long getLength() {
        return null;
    }

}
