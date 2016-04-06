package net.bodz.bas.ctx.scope.id;

import net.bodz.bas.t.preorder.DomainNamePreorder;

public class DomainScopeDescriptor
        extends PreorderScopeDescriptor<String> {

    public DomainScopeDescriptor(String domainName) {
        super(DomainNamePreorder.getInstance(), domainName);
    }

    public static DomainScopeDescriptor getInstance(String domainName) {
        return new DomainScopeDescriptor(domainName);
    }

}
