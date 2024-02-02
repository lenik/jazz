package net.bodz.bas.esm;

import net.bodz.bas.t.order.IPriority;

public abstract class EsmScope
        implements
            IPriority {

    int priority;

    public EsmScope() {
    }

    public EsmScope(int priority) {
        this.priority = priority;
    }

    public abstract EsmScopeType getType();

}
