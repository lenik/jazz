package net.bodz.bas.site.vhost;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;

public class VirtualHostScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHostOpt();
        if (vhost == null)
            return null;
        return new VirtualHostScopeInstance(vhost);
    }

}
