package net.bodz.bas.ctx.scope.id;

import java.util.HashMap;
import java.util.Map;

public class MutableScopeDescriptor
        extends AbstractScopeDescriptor {

    private String name;
    private Object identity;
    private IScopeDescriptor parent;
    private boolean transparent;
    private Map<String, Object> vars;

    public MutableScopeDescriptor(String name, Object identity) {
        this(name, identity, null);
    }

    public MutableScopeDescriptor(String name, Object identity, IScopeDescriptor root) {
        if (identity == null)
            throw new NullPointerException("identity");
        this.name = name;
        this.identity = identity;
        this.parent = root;
        this.vars = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public Object getIdentity() {
        return identity;
    }

    @Override
    public IScopeDescriptor getParent() {
        IScopeDescriptor internalParent = getInternalParent(identity);
        if (internalParent != null)
            return internalParent;
        else
            return parent;
    }

    public void setParent(IScopeDescriptor parent) {
        this.parent = parent;
    }

    protected IScopeDescriptor getInternalParent(Object identity) {
        return null;
    }

    @Override
    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    @Override
    public boolean contains(String name) {
        return vars.containsKey(name);
    }

    @Override
    public Object get(String name) {
        return vars.get(name);
    }

    @Override
    public void set(String name, Object value) {
        vars.put(name, value);
    }

    @Override
    public int hashCode() {
        return identity.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof IScopeDescriptor))
            return false;

        IScopeDescriptor o = (IScopeDescriptor) obj;
        return identity.equals(o.getIdentity());
    }

    @Override
    public String toString() {
        return name + "@" + getClass().getSimpleName();
    }

}
