package net.bodz.bas.fmt.rst;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;

public abstract class AbstractRstHandler
        implements IRstHandler {

    @Override
    public IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        return null;
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return false;
    }

    @Override
    public void complete(IRstElement element)
            throws ElementHandlerException {
    }

}
