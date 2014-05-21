package net.bodz.bas.context;

public class MutableContext
        extends AbstractContext {

    private String name;
    private Object identity;
    private IContext parent;
    private boolean transparent;

    public MutableContext(String name, Object identity) {
        this(name, identity, null);
    }

    public MutableContext(String name, Object identity, IContext root) {
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
    public IContext getParent() {
        IContext internalParent = getInternalParent(identity);
        if (internalParent != null)
            return internalParent;
        else
            return parent;
    }

    public void setParent(IContext parent) {
        this.parent = parent;
    }

    protected IContext getInternalParent(Object identity) {
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
        if (!(obj instanceof IContext))
            return false;

        IContext o = (IContext) obj;
        return identity.equals(o.getIdentity());
    }

    @Override
    public String toString() {
        return name + "@" + getClass().getSimpleName();
    }

}
