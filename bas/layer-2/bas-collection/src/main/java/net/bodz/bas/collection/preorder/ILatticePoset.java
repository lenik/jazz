package net.bodz.bas.collection.preorder;

public interface ILatticePoset<T>
        extends IPoset<T>, ILattice<T> {

    /**
     * @return The least upper bound of <code>{o}</code> in domain <code>T</code>.
     */
    T ceiling(T o);

}
