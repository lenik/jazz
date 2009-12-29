package net.bodz.bas.typeinfo.impl;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.exceptions.UnsupportedNegotiationException;
import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.type.IParser;

public abstract class SimpleParser<T>
        implements IParser<T> {

    @Override
    public final T parse(String text, INegotiation negotiation)
            throws ParseException, UnsupportedNegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

}
