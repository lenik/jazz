package net.bodz.bas.vfs.path;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import net.bodz.bas.util.order.IPriority;
import net.bodz.bas.util.order.PriorityComparator;

public class PathSystem
        implements IPathSystem {

    private final Map<String, IPathResolver> protocols;
    private final TreeSet<PathResolverKey> fallbacks;

    private IPath contextPath;

    public PathSystem() {
        protocols = new HashMap<String, IPathResolver>();
        fallbacks = new TreeSet<PathResolverKey>(PriorityComparator.INSTANCE);
    }

    @Override
    public boolean addPathResolver(String protocol, IPathResolver resolver) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        if (resolver == null)
            throw new NullPointerException("resolver");
        IPathResolver inuse = protocols.get(protocol);
        if (inuse != null) {
            return false;
        } else {
            protocols.put(protocol, resolver);
            return true;
        }
    }

    @Override
    public void removePathResolver(String protocol) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        protocols.remove(protocol);
    }

    @Override
    public void addGenericPathResolver(IGenericPathResolver resolver, int priority) {
        PathResolverKey key = new PathResolverKey(priority, resolver);
        fallbacks.add(key);
    }

    @Override
    public void removeGenericPathResolver(IGenericPathResolver resolver) {
        fallbacks.remove(resolver);
    }

    @Override
    public IPath resolve(String path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");
        int colon = path.indexOf(':');
        String protocol = colon == -1 ? null : path.substring(0, colon);

        IPathResolver explicitResolver = protocols.get(protocol);
        if (explicitResolver != null) {
            // assert explicitResolver.accepts(protocol);
            // String protocolSpecificPath = path.substring(colon + 1);
            return explicitResolver.resolve(path);
        }

        for (PathResolverKey key : fallbacks) {
            IGenericPathResolver resolver = key.resolver;
            if (!resolver.accepts(protocol))
                continue;
            IPath resolvedPath = resolver.resolve(path);
            return resolvedPath;
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
            throw new NullPointerException("defaultContext");
        this.contextPath = contextPath;
    }

}

class PathResolverKey
        implements IPriority {

    public final int priority;
    public final IGenericPathResolver resolver;

    public PathResolverKey(int priority, IGenericPathResolver resolver) {
        this.priority = priority;
        this.resolver = resolver;
    }

    @Override
    public int getPriority() {
        return priority;
    }

}
