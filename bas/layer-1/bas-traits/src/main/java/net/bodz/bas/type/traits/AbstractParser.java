package net.bodz.bas.type.traits;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.UnsupportedNegotiationException;
import net.bodz.bas.util.exception.ParseException;

public abstract class AbstractParser<T>
        implements IParser<T> {

    @Override
    public T parse(String text, INegotiation negotiation)
            throws ParseException, UnsupportedNegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

}
