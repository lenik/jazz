package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.util.IBeanResolver;


public abstract class AbstractScope
        implements IScopeTeller, IBeanResolver {

    @Override
    public int getPriority() {
        return 0;
    }

}
