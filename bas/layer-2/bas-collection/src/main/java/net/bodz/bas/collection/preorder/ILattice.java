package net.bodz.bas.collection.preorder;

public interface ILattice<T>
        extends IPreorder<T> {

    /**
     * @return The greatest value in lower bounds of <code>{o}</code> but excludes <code>{o}</code>
     *         in domain <code>T</code>. Return <code>null</code> if <code>{o}</code> is the max
     *         value.
     */
    T getSucceeding(T o);

    /**
     * @return The least upper bound of <code>array</code> in domain <code>T</code>.
     */
    @SuppressWarnings("unchecked")
    T join(T... array);

    /**
     * @return The least upper bound of <code>{o1, o2}</code> in domain <code>T</code>.
     */
    T join(T o1, T o2);

    class JoinX<T> {
        public T join;
        public T x1;
        public T x2;

        public JoinX(T join, T x1, T x2) {
            this.join = join;
            this.x1 = x1;
            this.x2 = x2;
        }
    }

    JoinX<T> joinX(T o1, T o2);

}
