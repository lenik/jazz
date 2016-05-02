package net.bodz.bas.ctx.scope;

import java.util.Map;


public abstract class MutableScopeInstance
        extends AbstractScopeInstance {

    private String name;
    private Object identity;
    private IScopeInstance parent;
    private boolean transparent;

    public MutableScopeInstance(String name, Object identity) {
        this(name, identity, null);
    }

    public MutableScopeInstance(String name, Object identity, IScopeInstance parent) {
        if (identity == null)
            throw new NullPointerException("identity");
        this.name = name;
        this.identity = identity;
        this.parent = parent;
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
    public IScopeInstance getParent() {
        IScopeInstance internalParent = getInternalParent(identity);
        if (internalParent != null)
            return internalParent;
        else
            return parent;
    }

    public void setParent(IScopeInstance parent) {
        this.parent = parent;
    }

    protected IScopeInstance getInternalParent(Object identity) {
        return null;
    }

    @Override
    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    protected abstract Map<String, Object> getVarMap();

    @Override
    public boolean contains(String name) {
        return getVarMap().containsKey(name);
    }

    @Override
    public Object get(String name) {
        return getVarMap().get(name);
    }

    @Override
    public void set(String name, Object value) {
        getVarMap().put(name, value);
    }

    @Override
    public void remove(String name) {
        getVarMap().remove(name);
    }

    @Override
    public int hashCode() {
        return identity.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof IScopeInstance))
            return false;

        IScopeInstance o = (IScopeInstance) obj;
        return identity.equals(o.getIdentity());
    }

    @Override
    public String toString() {
        return name + "@" + getClass().getSimpleName();
    }

}
