package net.bodz.bas.repr.path.builtin;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class RootResourcePathDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_CLASS_RESOURCE;
    static final Class<?> ACCEPT_TYPES[] = { Object.class };

    boolean mimeTypeFromExtension = true;
    boolean mimeTypeFromMagic = false;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public Class<?>[] getAcceptTypes() {
        return ACCEPT_TYPES;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (source == null)
            throw new PathDispatchException("null source.");

        if (source instanceof URLResource)
            return null;

        // must from the beginning.
        int nPrev = 0;
        for (IPathArrival p = previous; p != null; p = p.getPrevious())
            nPrev += p.getConsumedTokens().length;
        if (nPrev != 0)
            return null;

        String remaining = tokens.getRemainingPath();
        List<String> checkList = new ArrayList<String>();
        if (remaining == null || remaining.isEmpty()) {
            checkList.add("index.html");
        } else if (tokens.isEntered()) {
            checkList.add(remaining + "/index.html");
        } else {
            // the file name must have extension for mime-type.
            if (! remaining.contains("."))
                return null;
            checkList.add(remaining);
        }

        URL url = null;
        ClassLoader loader = source.getClass().getClassLoader();
        for (String chk : checkList) {
            url = loader.getResource(chk);
            if (url != null)
                break;
        }
        if (url == null)
            return null;

        URLResource resource = ResFn.url(url);
        String[] all = tokens.shiftAll();
        return new PathArrival(previous, this, resource, all, "");
    }

}
