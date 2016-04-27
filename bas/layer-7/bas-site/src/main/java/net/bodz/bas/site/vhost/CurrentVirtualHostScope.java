package net.bodz.bas.site.vhost;

import net.bodz.bas.ctx.scope.AbstractScope;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.ctx.scope.id.MutableScopeDescriptor;

public class CurrentVirtualHostScope
        extends AbstractScope {

    @Override
    public IScopeDescriptor tell() {
        IScopeDescriptor current = MutableScopeDescriptor.DEFAULT;

        IVirtualHost vhost = CurrentVirtualHost.getVirtualHostOpt();
        if (vhost != null) {
            VirtualHostScopeDescriptor descriptor = new VirtualHostScopeDescriptor(vhost);
            descriptor.setParent(current);
            current = descriptor;
        }

        return current;
    }

}
