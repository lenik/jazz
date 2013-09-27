package net.bodz.bas.io.res;

public abstract class AbstractStreamResource
        extends StreamResourceTemplate
        implements IStreamResource {

    @Override
    public long getLength() {
        return -1L;
    }

}
