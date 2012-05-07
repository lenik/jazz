package net.bodz.bas.context;

import net.bodz.bas.collection.preorder.DomainNamePreorder;

public class DomainContextId
        extends PreorderContextId<String> {

    public DomainContextId(String domainName) {
        this(StaticContextId.getInstance(), domainName);
    }

    public DomainContextId(IContextId fallbackContext, String domainName) {
        super(fallbackContext, DomainNamePreorder.getInstance(), domainName);
    }

    public static DomainContextId getInstance(String domainName) {
        return new DomainContextId(domainName);
    }

    public static DomainContextId getInstance(IContextId fallbackContext, String domainName) {
        return new DomainContextId(fallbackContext, domainName);
    }

}
