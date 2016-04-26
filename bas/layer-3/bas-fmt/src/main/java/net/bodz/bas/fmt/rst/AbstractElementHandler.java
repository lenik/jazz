package net.bodz.bas.fmt.rst;

import net.bodz.bas.err.ParseException;

public abstract class AbstractElementHandler
        implements IElementHandler {

    @Override
    public IElementHandler beginChild(String name, String[] args)
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
