package net.bodz.bas.mf.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.rtx.IOptions;

public class ParserUtil {

    public static <T> T parse(Class<T> objType, String text)
            throws ParseException {
        IParser<T> parser = MdaFeatures.getMdaFeature(objType, IParser.class);
        if (parser == null)
            throw new ParseException("Don't know how to parse " + objType);
        return parser.parse(text);
    }

    public static <T> T parse(Class<T> objType, String text, IOptions options)
            throws ParseException {
        IParser<T> parser = MdaFeatures.getMdaFeature(objType, IParser.class);
        if (parser == null)
            throw new ParseException("Don't know how to parse " + objType);
        return parser.parse(text);
    }

}
