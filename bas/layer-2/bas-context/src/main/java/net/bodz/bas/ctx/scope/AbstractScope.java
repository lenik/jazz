package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.ctx.util.IBeanResolver;

public abstract class AbstractScope
        implements IScopeTeller, IBeanResolver {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public final boolean contains(String name) {
        IScopeDescriptor current = tell();
        return current.contains(name);
    }

    @Override
    public final Object resolve(String name) {
        IScopeDescriptor current = tell();
        return current.get(name);
    }

}
