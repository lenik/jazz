package net.bodz.bas.collection.preorder;

/**
 * Preorder-set
 */
public interface IPoset<T>
        extends IPreorder<T> {

    boolean contains(T o);

    T getMinimum();

    /**
     * Get the max element in this poset of {@link T}, which is less than or equals to
     * <code>o</code> .
     * 
     * @return may be equals to <code>o</code>.
     */
    T floor(T o);

    /**
     * Get the preceding element of <code>o</code>, in this poset.
     * 
     * @return should be different to <code>o</code>.
     */
    @Override
    T getPreceding(T o);

}
