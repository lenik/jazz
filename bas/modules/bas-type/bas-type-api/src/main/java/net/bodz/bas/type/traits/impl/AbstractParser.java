package net.bodz.bas.type.traits.impl;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.exceptions.UnsupportedNegotiationException;
import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.type.traits.IParser;

public abstract class AbstractParser<T>
        implements IParser<T> {

    @Override
    public final T parse(String text, INegotiation negotiation)
            throws ParseException, UnsupportedNegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

}
