package net.bodz.bas.aspect.typeinfo;

import java.util.Map;

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
     * @return <code>null</code> if the sample space restricted by <code>classification</code> is
     *         empty.
     */
    T newSample(Map<String, Object> classification, Negotiation negotiation)
            throws CreateException, NegotiationException;

}
