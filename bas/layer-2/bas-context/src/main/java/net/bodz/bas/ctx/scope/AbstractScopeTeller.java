package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.util.IBeanResolver;

public abstract class AbstractScopeTeller
        implements IScopeTeller, IBeanResolver {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean contains(String name) {
        IScopeInstance current = tell();
        return current != null && current.contains(name);
    }

    @Override
    public Object resolve(String name) {
        IScopeInstance current = tell();
        if (current == null)
            return null;
        else
            return current.get(name);
    }

}
