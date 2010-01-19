package net.bodz.bas.arch.context;

import net.bodz.bas.collection.preorder.DomainNamePreorder;

public class DomainContext
        extends PreorderContext<String> {

    public DomainContext(IContext fallbackContext, String domainName) {
        super(fallbackContext, DomainNamePreorder.getInstance(), domainName);
    }

    public DomainContext(String domainName) {
        this(StaticContext.getInstance(), domainName);
    }

    public static DomainContext getInstance(IContext fallbackContext, String domainName) {
        return new DomainContext(fallbackContext, domainName);
    }

    public static DomainContext getInstance(String domainName) {
        return new DomainContext(StaticContext.getInstance(), domainName);
    }

}
