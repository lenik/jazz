package net.bodz.bas.traits;

import java.util.Map;
import java.util.Random;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.util.exception.CreateException;

public interface ISampleGenerator<T> {

    /**
     * @return non-<code>null</code> sample instance.
     */
    T newSample()
            throws CreateException;

    /**
     * A short string phrase describe the usage.
     * 
     * Examples:
     * <ul>
     * <li><code>"use-case"</code> for demo presentation (default)</li>
     * <li><code>"test"</code> for Unit Test</li>
     * <li><code>"performance"</code> for Performance Test</li>
     * </ul>
     */
    @ValueType(String.class)
    String sampleUsage = "sample.usage";

    /**
     * Negotiations:
     * <ul>
     * <li>Optional {@link #sampleUsage}: Generate samples for specified usage.
     * <li>Optional {@link Random}.class: Build random samples rather than meaningful ones, with the
     * specified {@link Random} generator when possible.
     * </ul>
     * 
     * @return <code>null</code> if the sample space restricted by <code>classification</code> is
     *         empty.
     */

    T newSample(Map<String, Object> classification, INegotiation negotiation)
            throws CreateException, NegotiationException;

}
