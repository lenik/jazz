package net.bodz.bas.context;

import net.bodz.bas.util.Nullables;

public abstract class AbstractContextId
        implements IContextId {

    private final String contextName;

    public AbstractContextId(String contextName) {
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
        if (obj instanceof AbstractContextId)
            return equals((AbstractContextId) obj);
        return false;
    }

    protected boolean equals(AbstractContextId obj) {
        IContextId parent = getParentContextId();
        IContextId oParent = obj.getParentContextId();
        return Nullables.equals(parent, oParent);
    }

    // private final InstanceId id = new InstanceId(getClass());

    @Override
    public String toString() {
        return contextName + "@" + getClass().getSimpleName();
    }

}
