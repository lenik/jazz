package net.bodz.bas.traits;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.MandatoryException;
import net.bodz.bas.util.exception.ParseException;

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
