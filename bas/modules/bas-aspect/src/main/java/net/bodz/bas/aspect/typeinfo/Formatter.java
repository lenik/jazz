package net.bodz.bas.aspect.typeinfo;

import java.io.Writer;

import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.types.Negotiation;

public interface Formatter<T> {

    String format(T object);

    /**
     * Negotiation:
     * <ul>
     * <li>write to a {@link Writer} and maybe ignore the return value
     * <li>continuation format/reset
     * <li>code table
     * <li>error handler/recover
     * </ul>
     */
    String format(T object, Negotiation negotiation)
            throws NegotiationException;

}
