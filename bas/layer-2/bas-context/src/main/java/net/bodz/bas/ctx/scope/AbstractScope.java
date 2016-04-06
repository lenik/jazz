package net.bodz.bas.ctx.scope;


public abstract class AbstractScope
        implements IScopeTeller, IBeanResolver {

    @Override
    public int getPriority() {
        return 0;
    }

}
