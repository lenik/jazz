package net.bodz.bas.traits;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.MandatoryException;

public abstract class AbstractParser<T>
        implements IParser<T> {

    @Override
    public T parse(String text, INegotiation negotiation)
            throws ParseException, MandatoryException {
        if (negotiation != null)
            negotiation.bypass();
        return parse(text);
    }

}
