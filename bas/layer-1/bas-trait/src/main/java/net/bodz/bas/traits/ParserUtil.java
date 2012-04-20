package net.bodz.bas.traits;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.trait.Traits;

public class ParserUtil {

    public static <T> T parse(Class<T> objType, String text)
            throws ParseException {
        IParser<T> parser = Traits.getTrait(objType, IParser.class);
        return parser.parse(text);
    }

    public static <T> T parse(Class<T> objType, String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        IParser<T> parser = Traits.getTrait(objType, IParser.class);
        return parser.parse(text);
    }

}
