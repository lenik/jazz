package net.bodz.bas.ctx.scope;

import net.bodz.bas.t.preorder.IPreorder;

public class PreorderScopeToken<T>
        extends MutableScopeToken {

    private final IPreorder<T> preorder;

    /**
     * @throws NullPointerException
     *             If <code>preorder</code> is <code>null</code>.
     */
    public PreorderScopeToken(IPreorder<T> preorder, T node) {
        super(String.valueOf(node), node);
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    protected IScopeToken getInternalParent(Object identity) {
        @SuppressWarnings("unchecked")
        T node = (T) identity;
        T preceding = preorder.getPreceding(node);
        if (preceding == null)
            return null;
        else
            return new PreorderScopeToken<T>(preorder, preceding);
    }

}
