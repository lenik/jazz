package net.bodz.bas.context;

import net.bodz.bas.collection.preorder.DomainNamePreorder;

public class DomainContext
        extends PreorderContext<String> {

    public DomainContext(String domainName) {
        this(StaticContext.getInstance(), domainName);
    }

    public DomainContext(IContext fallbackContext, String domainName) {
        super(fallbackContext, DomainNamePreorder.getInstance(), domainName);
    }

    public static DomainContext getInstance(String domainName) {
        return new DomainContext(domainName);
    }

    public static DomainContext getInstance(IContext fallbackContext, String domainName) {
        return new DomainContext(fallbackContext, domainName);
    }

}
