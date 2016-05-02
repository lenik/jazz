package net.bodz.bas.site.vhost;

import java.util.Map;

import net.bodz.bas.ctx.scope.MutableScopeInstance;

public class VirtualHostScopeInstance
        extends MutableScopeInstance {

    private IVirtualHost vhost;

    public VirtualHostScopeInstance(IVirtualHost vhost) {
        super(vhost.getName(), vhost);
        this.vhost = vhost;
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return null;
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

    @Override
    public void remove(String name) {
        vhost.removeAttribute(name);
    }

}
