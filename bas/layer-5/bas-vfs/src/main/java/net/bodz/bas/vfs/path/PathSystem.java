package net.bodz.bas.vfs.path;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class PathSystem
        implements IPathSystem {

    static class PathResolverKey
            implements Comparable<PathResolverKey> {

        public final int priority;
        public final IPathResolver resolver;

        public PathResolverKey(int priority, IPathResolver resolver) {
            this.priority = priority;
            this.resolver = resolver;
        }

        @Override
        public int compareTo(PathResolverKey o) {
            return priority - o.priority;
        }

    }

    private final Map<String, IPathResolver> protocols;
    private final TreeSet<PathResolverKey> generics;

    private IPath contextPath;

    public PathSystem() {
        protocols = new HashMap<String, IPathResolver>();
        generics = new TreeSet<PathResolverKey>();
    }

    @Override
    public void addPathResolver(String protocol, IPathResolver resolver) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        if (resolver == null)
            throw new NullPointerException("resolver");
        protocols.put(protocol, resolver);
    }

    @Override
    public void removePathResolver(String protocol) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        protocols.remove(protocol);
    }

    @Override
    public void addGenericPathResolver(IPathResolver resolver, int priority) {
        PathResolverKey key = new PathResolverKey(priority, resolver);
        generics.add(key);
    }

    @Override
    public void addGenericPathResolver(IPathResolver resolver) {
        addGenericPathResolver(resolver, IPathSystem.NORMAL_PRIORITY);
    }

    @Override
    public void removeGenericPathResolver(IPathResolver resolver) {
        generics.remove(resolver);
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

        for (PathResolverKey key : generics) {
            IPathResolver genericResolver = key.resolver;
            if (!genericResolver.accepts(protocol))
                continue;
            IPath resolvedPath = genericResolver.resolve(path);
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
