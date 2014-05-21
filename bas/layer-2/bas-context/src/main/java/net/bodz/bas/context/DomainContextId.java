package net.bodz.bas.context;

import net.bodz.bas.t.preorder.DomainNamePreorder;

public class DomainContextId
        extends PreorderContext<String> {

    public DomainContextId(String domainName) {
        super(DomainNamePreorder.getInstance(), domainName);
    }

    public static DomainContextId getInstance(String domainName) {
        return new DomainContextId(domainName);
    }

}
