package net.bodz.bas.traits;

import java.beans.ExceptionListener;
import java.text.ParsePosition;
import java.util.logging.Logger;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.util.exception.ParseException;

public interface IParser<T> {

    int traitsIndex = -1548284472; // IParser

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
