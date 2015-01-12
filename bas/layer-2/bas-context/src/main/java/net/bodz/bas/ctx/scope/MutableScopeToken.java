package net.bodz.bas.ctx.scope;

public class MutableScopeToken
        extends AbstractScopeToken {

    private String name;
    private Object identity;
    private IScopeToken parent;
    private boolean transparent;

    public MutableScopeToken(String name, Object identity) {
        this(name, identity, null);
    }

    public MutableScopeToken(String name, Object identity, IScopeToken root) {
        if (identity == null)
            throw new NullPointerException("identity");
        this.name = name;
        this.identity = identity;
        this.parent = root;
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
    public IScopeToken getParent() {
        IScopeToken internalParent = getInternalParent(identity);
        if (internalParent != null)
            return internalParent;
        else
            return parent;
    }

    public void setParent(IScopeToken parent) {
        this.parent = parent;
    }

    protected IScopeToken getInternalParent(Object identity) {
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
    public int hashCode() {
        return identity.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof IScopeToken))
            return false;

        IScopeToken o = (IScopeToken) obj;
        return identity.equals(o.getIdentity());
    }

    @Override
    public String toString() {
        return name + "@" + getClass().getSimpleName();
    }

}
