package net.bodz.bas.ctx.inject;

import net.bodz.bas.ctx.scope.IScopeTeller;

public abstract class AbstractScope
        implements IScopeTeller, IBeanResolver {

    @Override
    public int getPriority() {
        return 0;
    }

}
