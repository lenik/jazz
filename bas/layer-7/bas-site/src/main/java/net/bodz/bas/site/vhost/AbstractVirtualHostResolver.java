package net.bodz.bas.site.vhost;

public abstract class AbstractVirtualHostResolver
        implements IVirtualHostResolver {

    @Override
    public int getPriority() {
        return 0;
    }

}
