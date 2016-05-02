package net.bodz.bas.ctx.scope.impl;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.t.preorder.IPreorder;

public class PreorderScopeInstance<T>
        extends ObjScopeInstance {

    private final IPreorder<T> preorder;

    /**
     * @throws NullPointerException
     *             If <code>preorder</code> is <code>null</code>.
     */
    public PreorderScopeInstance(IPreorder<T> preorder, T node) {
        super(String.valueOf(node), node);
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    protected IScopeInstance getInternalParent(Object identity) {
        @SuppressWarnings("unchecked")
        T node = (T) identity;
        T preceding = preorder.getPreceding(node);
        if (preceding == null)
            return null;
        else
            return new PreorderScopeInstance<T>(preorder, preceding);
    }

}
