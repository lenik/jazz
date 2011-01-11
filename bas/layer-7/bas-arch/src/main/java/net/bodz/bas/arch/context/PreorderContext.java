package net.bodz.bas.arch.context;

import net.bodz.bas.collection.preorder.IPreorder;
import net.bodz.bas.lang.Nullables;

public class PreorderContext<T>
        extends AbstractContext {

    private final IContext fallbackContext;
    private final IPreorder<T> preorder;
    private final T node;

    /**
     * @throws NullPointerException
     *             If <code>preorder</code> is <code>null</code>.
     */
    public PreorderContext(IContext fallbackContext, IPreorder<T> preorder, T node) {
        super(String.valueOf(node));
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.fallbackContext = fallbackContext;
        this.preorder = preorder;
        this.node = node;
    }

    @Override
    public IContext getParentContext() {
        T preceding = preorder.getPreceding(node);
        if (preceding == null)
            return fallbackContext;
        return new PreorderContext<T>(fallbackContext, preorder, preceding);
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
        if (!(obj instanceof PreorderContext<?>))
            return false;
        PreorderContext<?> o = (PreorderContext<?>) obj;
        if (!preorder.equals(o.preorder))
            return false;
        if (!Nullables.equals(node, o.node))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        return true;
    }

}
