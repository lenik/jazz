package net.bodz.bas.io.res;

import java.io.IOException;

import net.bodz.bas.meta.decl.NotNull;

public abstract class ProxyRandomResource
        extends ProxyStreamResource<ProxyRandomResource>
        implements IRandomResource {

    public ProxyRandomResource(IRandomResource _orig) {
        super(_orig);
    }

    @NotNull
    public IRandomResource getWrapped() {
        return (IRandomResource) _orig;
    }

    @Override
    public Long getLength()
            throws IOException {
        return getWrapped().getLength();
    }

}
