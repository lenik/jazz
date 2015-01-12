package net.bodz.bas.ctx.scope;

import net.bodz.bas.t.preorder.DomainNamePreorder;

public class DomainScopeToken
        extends PreorderScopeToken<String> {

    public DomainScopeToken(String domainName) {
        super(DomainNamePreorder.getInstance(), domainName);
    }

    public static DomainScopeToken getInstance(String domainName) {
        return new DomainScopeToken(domainName);
    }

}
