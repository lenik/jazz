package net.bodz.bas.mf.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.rtx.MandatoryException;

public abstract class AbstractParser<T>
        implements IParser<T> {

    @Override
    public T parse(String text, INegotiation negotiation)
            throws ParseException, MandatoryException {
        if (negotiation != null)
            negotiation.ignore();
        return parse(text);
    }

}
