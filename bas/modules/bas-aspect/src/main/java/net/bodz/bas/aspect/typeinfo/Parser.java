package net.bodz.bas.aspect.typeinfo;

import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.Negotiation;

public interface Parser<T> {

    T parse(String text)
            throws ParseException;

    /**
     * Negotiations:
     * 
     * <ul>
     * <li>text start/end position
     * <li>parse context, such as namespace resolvers
     * <li>parse continuation/reset, and last end position
     * <li>error handler/recover/user-interaction
     * <li>info logger
     * </ul>
     */
    T parse(String text, Negotiation negotiation)
            throws ParseException, NegotiationException;

}
