package net.bodz.bas.ctx.scope.impl;

import net.bodz.bas.t.preorder.DomainNamePreorder;

public class DomainScopeInstance
        extends PreorderScopeInstance<String> {

    public DomainScopeInstance(String domainName) {
        super(DomainNamePreorder.getInstance(), domainName);
    }

    public static DomainScopeInstance getInstance(String domainName) {
        return new DomainScopeInstance(domainName);
    }

}
