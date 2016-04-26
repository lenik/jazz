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

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_CLASS_RESOURCE;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String remaining = tokens.getRemainingPath();
        if (remaining == null || remaining.isEmpty()) // XXX can it be empty?
            return null;

        // the file name must have extension.
        if (!remaining.contains("."))
            return null;

        Class<?> clazz = obj.getClass();
        URL url = null;
        while (clazz != null) {
            String packageName = clazz.getPackage().getName();
            if (packageName.startsWith("java."))
                break;

            url = clazz.getResource(remaining);
            if (url != null)
                break;
            clazz = clazz.getSuperclass();
        }
        if (url == null)
            return null;

        URLResource resource = new URLResource(url);
        return new PathArrival(previous, resource, tokens.shiftAll(), "");
    }

}
