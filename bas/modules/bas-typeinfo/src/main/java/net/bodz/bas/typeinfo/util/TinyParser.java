package net.bodz.bas.typeinfo.util;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.exceptions.UnsupportedNegotiationException;
import net.bodz.bas.api.types.Negotiation;
import net.bodz.bas.typeinfo.Parser;

public abstract class TinyParser<T>
        implements Parser<T> {

    @Override
    public final T parse(String text, Negotiation negotiation)
            throws ParseException, UnsupportedNegotiationException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

}
