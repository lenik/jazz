package net.bodz.bas.collection.preorder;

import java.util.Comparator;

public interface IPreorder<T>
        extends Comparator<T> {

    /**
     * Compare in dictionary order.
     * 
     * This comparation is used to flatten the preorder tree into dictionary order for sorting.
     */
    @Override
    int compare(T o1, T o2);

    int LESS_THAN = -1;
    int EQUALS = 0;
    int GREATER_THAN = 1;
    int UNKNOWN = Integer.MAX_VALUE;

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
     * @see #LESS_THAN
     * @see #EQUALS
     * @see #GREATER_THAN
     * @see #UNKNOWN
     */
    int precompare(T o1, T o2);

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
     */
    boolean isLessThan(T o1, T o2);

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
     */
    boolean isGreaterThan(T o1, T o2);

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
     */
    boolean isLessOrEquals(T o1, T o2);

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
     */
    boolean isGreaterOrEquals(T o1, T o2);

    /**
     * @param o
     *            non-<code>null</code> value.
     * @return The lowest value in upper bounds of <code>{o}</code> but excludes <code>{o}</code> in
     *         domain <code>T</code>. Return <code>null</code> if <code>{o}</code> is the min value.
     * @throws NullPointerException
     *             If <code>o</code> is <code>null</code>.
     */
    T getPreceding(T o);

    /**
     * @param array
     *            Non-empty array.
     * @return The greatest lower bound of <code>array</code> in domain <code>T</code>.
     * @throws NullPointerException
     *             If array is <code>null</code>.
     * @throws IllegalArgumentException
     *             If array is empty.
     */
    T meet(T... array);

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @return The greatest lower bound of <code>{o1, o2}</code> in domain <code>T</code> .
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
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

    /**
     * @param o1
     *            non-<code>null</code> value.
     * @param o2
     *            non-<code>null</code> value.
     * @return non-<code>null</code> {@link MeetX} struct.
     * @throws NullPointerException
     *             if any parameter is <code>null</code>.
     */
    MeetX<T> meetX(T o1, T o2);

}
