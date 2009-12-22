package net.bodz.bas.typeinfo;

import java.beans.ExceptionListener;
import java.text.ParsePosition;
import java.util.logging.Logger;

import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.Negotiation;

public interface Parser<T> {

    T parse(String text)
            throws ParseException;

    String PARSE_CONTEXT = "ParseContext";

    /**
     * Negotiations:
     * 
     * <ul>
     * <li><b>Mandatory</b> {@link ParsePosition}: text start/end position
     * <li><b>Mandatory</b> {@link #PARSE_CONTEXT}: parse context, such as namespace resolvers
     * <li><b>Mandatory</b> {@link Continuation}: context object used for continuation.
     * <li>Optional {@link ExceptionListener}: error handler/recover/user-interaction
     * <li>Optional {@link Logger}: info logger
     * </ul>
     */
    T parse(String text, Negotiation negotiation)
            throws ParseException, NegotiationException;

}
