package net.bodz.bas.ctx.scope.id;

public abstract class AbstractScopeDescriptor
        implements IScopeDescriptor {

    @Override
    public final IScopeDescriptor getRoot() {
        IScopeDescriptor node = this;
        IScopeDescriptor parent = getParent();
        while (parent != null) {
            node = parent;
            parent = node.getParent();
        }
        return node;
    }

}
