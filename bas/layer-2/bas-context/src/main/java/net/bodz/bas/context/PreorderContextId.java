package net.bodz.bas.context;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.t.preorder.IPreorder;

public class PreorderContextId<T>
        extends AbstractContextId {

    private final IContextId fallbackContext;
    private final IPreorder<T> preorder;
    private final T node;

    /**
     * @throws NullPointerException
     *             If <code>preorder</code> is <code>null</code>.
     */
    public PreorderContextId(IContextId fallbackContext, IPreorder<T> preorder, T node) {
        super(String.valueOf(node));
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.fallbackContext = fallbackContext;
        this.preorder = preorder;
        this.node = node;
    }

    @Override
    public IContextId getParentContextId() {
        T preceding = preorder.getPreceding(node);
        if (preceding == null)
            return fallbackContext;
        return new PreorderContextId<T>(fallbackContext, preorder, preceding);
    }

    @Override
    public int hashCode() {
        int hash = preorder.hashCode();
        if (node != null)
            hash += node.hashCode();
        if (fallbackContext != null)
            hash += fallbackContext.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PreorderContextId<?>))
            return false;
        PreorderContextId<?> o = (PreorderContextId<?>) obj;
        if (!preorder.equals(o.preorder))
            return false;
        if (!Nullables.equals(node, o.node))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        return true;
    }

}
