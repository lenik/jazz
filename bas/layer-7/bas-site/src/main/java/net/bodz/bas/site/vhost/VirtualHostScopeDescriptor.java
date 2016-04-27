package net.bodz.bas.site.vhost;

import net.bodz.bas.ctx.scope.id.MutableScopeDescriptor;

public class VirtualHostScopeDescriptor
        extends MutableScopeDescriptor {

    private IVirtualHost vhost;

    public VirtualHostScopeDescriptor(IVirtualHost vhost) {
        super(vhost.getName(), vhost);
        this.vhost = vhost;
    }

    @Override
    public boolean contains(String name) {
        return vhost.getAttribute(name) != null;
    }

    @Override
    public Object get(String name) {
        return vhost.getAttribute(name);
    }

    @Override
    public void set(String name, Object value) {
        vhost.setAttribute(name, value);
    }

}
