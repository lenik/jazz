package net.bodz.bas.t.preorder;

public interface ILatticePoset<T>
        extends IPoset<T>, ILattice<T> {

    /**
     * @return The least upper bound of <code>{o}</code> in domain <code>T</code>.
     */
    T ceiling(T o);

}
