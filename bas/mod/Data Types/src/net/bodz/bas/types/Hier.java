package net.bodz.bas.types;

public interface Hier<E> {

    /**
     * @param sup
     *            never be <code>null</code>
     * @param sub
     *            never be <code>null</code>
     * @return true if sup (floor-ward) derives sub (ceiling-ward)
     */
    boolean derives(E sup, E sub);

    /**
     * @param e
     *            value to be shrinked (ceiling-ward), never be
     *            <code>null</code>
     * @return shrinked value (floor-ward), <code>null</code> if no more shrink
     */
    E shrink(E e);

}
