package net.bodz.bas.typer.std;

import java.text.ParsePosition;
import java.util.logging.Logger;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;

import com.googlecode.openbeans.ExceptionListener;

public interface IParser<T>
        extends IStdTyper {

    int typerIndex = 0x59ce1f03; // IParser

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
    T parse(String text, IOptions options)
            throws ParseException;

}
