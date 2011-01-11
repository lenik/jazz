package net.bodz.bas.type.traits;

import java.beans.ExceptionListener;
import java.text.ParsePosition;
import java.util.logging.Logger;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;

public interface IParser<T> {

    /**
     * @return ? extends <code>T</code>
     */
    T parse(String text)
            throws ParseException;

    String PARSE_CONTEXT = "ParseContext";

    /**
     * Negotiations:
     * 
     * <ul>
     * <li><b>Mandatory</b> {@link ParsePosition}: text start/end position
     * <li><b>Mandatory</b> {@link #PARSE_CONTEXT}: parse context, such as namespace resolvers
     * <li><b>Mandatory</b> {@link IContinuation}: context object used for continuation.
     * <li>Optional {@link ExceptionListener}: error handler/recover/user-interaction
     * <li>Optional {@link Logger}: info logger
     * </ul>
     * 
     * @return ? extends <code>T</code>
     */
    T parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException;

}
