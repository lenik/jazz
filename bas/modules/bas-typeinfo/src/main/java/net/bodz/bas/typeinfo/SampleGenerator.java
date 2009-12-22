package net.bodz.bas.typeinfo;

import java.util.Map;
import java.util.Random;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.types.Negotiation;

public interface SampleGenerator<T> {

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
    String SAMPLE_USAGE = "SampleUsage";

    /**
     * Negotiations:
     * <ul>
     * <li>Optional {@link #SAMPLE_USAGE}: Generate samples for specified usage.
     * <li>Optional {@link Random}.class: Build random samples rather than meaningful ones, with the
     * specified {@link Random} generator when possible.
     * </ul>
     * 
     * @return <code>null</code> if the sample space restricted by <code>classification</code> is
     *         empty.
     */

    T newSample(Map<String, Object> classification, Negotiation negotiation)
            throws CreateException, NegotiationException;

}
