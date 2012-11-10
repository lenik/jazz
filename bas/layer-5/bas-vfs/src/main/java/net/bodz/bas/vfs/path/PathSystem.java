package net.bodz.bas.vfs.path;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import net.bodz.bas.util.order.IPriority;
import net.bodz.bas.util.order.PriorityComparator;
import net.bodz.bas.vfs.context.VFSColos;

public class PathSystem
        implements IPathSystem {

    private final Map<String, IPathParser> protocols;
    private final TreeSet<PathParserKey> fallbacks;

    private IPath contextPath;

    public PathSystem() {
        protocols = new HashMap<String, IPathParser>();
        fallbacks = new TreeSet<PathParserKey>(PriorityComparator.INSTANCE);

        contextPath = VFSColos.workdir.get().getPath();
    }

    @Override
    public boolean addPathParser(String protocol, IPathParser resolver) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        if (resolver == null)
            throw new NullPointerException("resolver");
        IPathParser inuse = protocols.get(protocol);
        if (inuse != null) {
            return false;
        } else {
            protocols.put(protocol, resolver);
            return true;
        }
    }

    @Override
    public void removePathParser(String protocol) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        protocols.remove(protocol);
    }

    @Override
    public void addGenericPathParser(IGenericPathParser resolver, int priority) {
        PathParserKey key = new PathParserKey(priority, resolver);
        fallbacks.add(key);
    }

    @Override
    public void removeGenericPathParser(IGenericPathParser resolver) {
        fallbacks.remove(resolver);
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");
        int colon = path.indexOf(':');
        String protocol = colon == -1 ? null : path.substring(0, colon);

        IPathParser explicitResolver = protocols.get(protocol);
        if (explicitResolver != null) {
            // assert explicitResolver.accepts(protocol);
            // String protocolSpecificPath = path.substring(colon + 1);
            return explicitResolver.parse(path);
        }

        for (PathParserKey key : fallbacks) {
            IGenericPathParser resolver = key.resolver;
            if (!resolver.accepts(protocol))
                continue;
            IPath parsedPath = resolver.parse(path);
            return parsedPath;
        }

        IPath context = getContextPath();
        return context.join(path);
    }

    @Override
    public IPath getContextPath() {
        if (contextPath == null)
            throw new IllegalStateException("Default context wasn't set");
        return contextPath;
    }

    @Override
    public void setContextPath(IPath contextPath) {
        if (contextPath == null)
            throw new NullPointerException("contextPath");
        this.contextPath = contextPath;
    }

}

class PathParserKey
        implements IPriority {

    public final int priority;
    public final IGenericPathParser resolver;

    public PathParserKey(int priority, IGenericPathParser resolver) {
        this.priority = priority;
        this.resolver = resolver;
    }

    @Override
    public int getPriority() {
        return priority;
    }

}
