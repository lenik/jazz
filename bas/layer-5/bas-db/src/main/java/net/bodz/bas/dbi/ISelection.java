package net.bodz.bas.dbi;

public interface ISelection<T> {

    int count();

    Iterable<T> read();

    void update(String expr);

    int delete();

    /**
     * @param first
     *            Index of the first result.
     * @param max
     *            Max results.
     */
    ISelection<T> limit(int first, int max);

    ISelection<T> limit(Object criteria);

}
