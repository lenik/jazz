package net.bodz.bas.typer.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.Typers;

public class ParserUtil {

    public static <T> T parse(Class<T> objType, String text)
            throws ParseException {
        IParser<T> parser = Typers.getTyper(objType, IParser.class);
        if (parser == null)
            throw new ParseException("Don't know how to parse " + objType);
        return parser.parse(text);
    }

    public static <T> T parse(Class<T> objType, String text, IOptions options)
            throws ParseException {
        IParser<T> parser = Typers.getTyper(objType, IParser.class);
        if (parser == null)
            throw new ParseException("Don't know how to parse " + objType);
        return parser.parse(text);
    }

}
