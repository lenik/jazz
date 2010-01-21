package net.bodz.bas.type.traits;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.UnsupportedNegotiationException;

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
