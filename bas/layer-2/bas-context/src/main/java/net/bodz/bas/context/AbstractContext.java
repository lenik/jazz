package net.bodz.bas.context;

public abstract class AbstractContext
        implements IContext {

    @Override
    public final IContext getRoot() {
        IContext ctx = this;
        IContext parent = getParent();
        while (parent != null) {
            ctx = parent;
            parent = ctx.getParent();
        }
        return ctx;
    }

    @Override
    public boolean contains(String name) {
        return false;
    }

    @Override
    public Object get(String name) {
        return null;
    }

    @Override
    public void set(String name, Object value) {
    }

}
