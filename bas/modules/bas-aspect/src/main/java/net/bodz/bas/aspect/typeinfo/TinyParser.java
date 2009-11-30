package net.bodz.bas.aspect.typeinfo;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.exceptions.UnsupportedNegotiationException;
import net.bodz.bas.api.types.Negotiation;

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
