package net.bodz.bas.repr.path.builtin;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.meta.RealType;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class ClassResourcePathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_CLASS_RESOURCE;

    boolean mimeTypeFromExtension = true;
    boolean mimeTypeFromMagic = false;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String remaining = tokens.getRemainingPath();
        List<String> checkList = new ArrayList<String>();
        if (remaining == null || remaining.isEmpty()) {
            checkList.add("index.html");
        } else if (tokens.isEntered()) {
            checkList.add(remaining + "/index.html");
        } else {
            // the file name must have extension for mime-type.
            if (!remaining.contains("."))
                return null;
            checkList.add(remaining);
        }

        Class<?> clazz = obj.getClass();
        URL url = null;

        // XXX VhostCluster workarounds...
        RealType _realType = clazz.getAnnotation(RealType.class);
        if (_realType != null)
            clazz = _realType.value();

        L: while (clazz != null && !shouldStop(clazz)) {
            String packageName = clazz.getPackage().getName();
            if (packageName.startsWith("java."))
                break;

            for (String chk : checkList) {
                url = clazz.getResource(chk);
                if (url != null)
                    break L;
            }

            clazz = clazz.getSuperclass();
        }
        if (url == null)
            return null;

        URLResource resource = new URLResource(url);
        return new PathArrival(previous, resource, tokens.shiftAll(), "");
    }

    protected boolean shouldStop(Class<?> clazz) {
        String fqcn = clazz.getName();
        if (fqcn.startsWith("java."))
            return true;
        return false;
    }

}
