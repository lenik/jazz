package net.bodz.bas.repr.position;

public interface IObjectPositionProvider {

    /**
     * @param maxOccurrences
     *            0 if no limit.
     * @throws IllegalArgumentException
     *             If <code>maxOccurrences</code> is negative.
     */
    IObjectOccurrences find(Object object, int maxOccurrences);

}
