package net.bodz.bas.ctx.scope;


public abstract class AbstractScopeInstance
        implements IScopeInstance {

    @Override
    public final IScopeInstance getRoot() {
        IScopeInstance node = this;
        IScopeInstance parent = getParent();
        while (parent != null) {
            node = parent;
            parent = node.getParent();
        }
        return node;
    }

}
