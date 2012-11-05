package net.bodz.bas.traits;

import java.beans.ExceptionListener;
import java.util.Iterator;

import net.bodz.bas.lang.negotiation.INegotiation;

public interface ISearcher<T> {

    int traitIndex = -2141867074; // ISearcher

    /**
     * @param object
     *            non-<code>null</code> contents to be searched.
     * @param query
     *            Query string which usually contains the keywords to be searched.
     * @return non-<code>null</code> {@link Iterator} of search result.
     */
    Iterator<?> search(T object, String query);

    /**
     * Negotiation:
     * <ul>
     * <li>Optional {@link ExceptionListener}: error handler/recover
     * </ul>
     * 
     * @param object
     *            non-<code>null</code> contents to be searched.
     * @param query
     *            Query string which usually contains the keywords to be searched.
     * @return non-<code>null</code> {@link Iterator} of search result.
     */
    Iterator<?> search(T object, String query, INegotiation negotiation);

}
