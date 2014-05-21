package net.bodz.bas.context;

import net.bodz.bas.t.preorder.IPreorder;

public class PreorderContext<T>
        extends MutableContext {

    private final IPreorder<T> preorder;

    /**
     * @throws NullPointerException
     *             If <code>preorder</code> is <code>null</code>.
     */
    public PreorderContext(IPreorder<T> preorder, T node) {
        super(String.valueOf(node), node);
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    protected IContext getInternalParent(Object identity) {
        @SuppressWarnings("unchecked")
        T node = (T) identity;
        T preceding = preorder.getPreceding(node);
        if (preceding == null)
            return null;
        else
            return new PreorderContext<T>(preorder, preceding);
    }

}
