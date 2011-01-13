package net.bodz.bas.arch.context;

import net.bodz.bas.util.Nullables;

public abstract class AbstractContext
        implements IContext {

    private final String contextName;

    public AbstractContext(String contextName) {
        if (contextName == null)
            throw new NullPointerException("contextName");
        this.contextName = contextName;
    }

    public String getContextName() {
        return contextName;
    }

    @Override
    public boolean isTransient() {
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AbstractContext)
            return equals((AbstractContext) obj);
        return false;
    }

    protected boolean equals(AbstractContext obj) {
        IContext parent = getParentContext();
        IContext oParent = obj.getParentContext();
        return Nullables.equals(parent, oParent);
    }

    // private final InstanceId id = new InstanceId(getClass());

    @Override
    public String toString() {
        return contextName + "@" + getClass().getSimpleName();
    }

}
