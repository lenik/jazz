package net.bodz.bas.fmt.rst;

import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;

public abstract class StackRstHandler
        implements
            IRstHandler {

    protected final LinkedList<String> stack = new LinkedList<>();
    protected String parent;

    @Override
    public IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        stack.push(parent = name);
        return this;
    }

    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        parent = stack.pop();
        return true;
    }

}
