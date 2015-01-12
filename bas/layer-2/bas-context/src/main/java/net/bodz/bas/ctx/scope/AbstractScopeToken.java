package net.bodz.bas.ctx.scope;

public abstract class AbstractScopeToken
        implements IScopeToken {

    @Override
    public final IScopeToken getRoot() {
        IScopeToken ctx = this;
        IScopeToken parent = getParent();
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
