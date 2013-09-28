package net.bodz.bas.io.res;

import java.io.IOException;

public abstract class ProxyRandomResource
        extends ProxyStreamResource
        // implements IWrapper<IRandomResource>
        implements IRandomResource {

    public ProxyRandomResource(IRandomResource _orig) {
        super(_orig);
    }

    public IRandomResource getWrapped()
            throws IOException {
        return (IRandomResource) _orig;
    }

    @Override
    public long getLength()
            throws IOException {
        return getWrapped().getLength();
    }

}
