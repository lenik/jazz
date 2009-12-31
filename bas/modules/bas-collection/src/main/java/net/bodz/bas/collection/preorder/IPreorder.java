package net.bodz.bas.collection.preorder;

public interface IPreorder<T> {

    int LESS_THAN = -1;
    int EQUALS = 0;
    int GREATER_THAN = 1;
    int UNKNOWN = Integer.MAX_VALUE;

    /**
     * @see #LESS_THAN
     * @see #EQUALS
     * @see #GREATER_THAN
     * @see #UNKNOWN
     */
    int precompare(T o1, T o2);

    boolean isLessThan(T o1, T o2);

    boolean isGreaterThan(T o1, T o2);

    boolean isLessOrEquals(T o1, T o2);

    boolean isGreaterOrEquals(T o1, T o2);

    /**
     * @return The lowest value in upper bounds of <code>{o}</code> but excludes <code>{o}</code> in
     *         domain <code>T</code>. Return <code>null</code> if <code>{o}</code> is the min value.
     */
    T getPreceding(T o);

    /**
     * @return The greatest lower bound of <code>array</code> in domain <code>T</code>.
     */
    T meet(T... array);

    /**
     * @return The greatest lower bound of <code>{o1, o2}</code> in domain <code>T</code> .
     */
    T meet(T o1, T o2);

    class MeetX<T> {
        T meet;
        T x1;
        T x2;

        public MeetX(T meet, T x1, T x2) {
            this.meet = meet;
            this.x1 = x1;
            this.x2 = x2;
        }

    }

    MeetX<T> meetX(T o1, T o2);

}
