package net.bodz.bas.repr.path.builtin;

import java.net.URL;

import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

public class ClassResourcePathDispatcher
        extends AbstractPathDispatcher {

    @Override
    public int getPriority() {
        return BuiltinPathDispatcherPriorities.PRIORITY_CLASS_RESOURCE;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String remaining = tokens.getRemainingPath();
        if (remaining == null || remaining.isEmpty()) // XXX can it be empty?
            return previous;

        // the file name must have extension.
        if (!remaining.contains("."))
            return null;

        Class<?> clazz = obj.getClass();
        URL url = clazz.getResource(remaining);
        if (url == null)
            return null;

        URLResource resource = new URLResource(url);
        return new PathArrival(previous, resource, tokens.shiftAll(), "");
    }

}
