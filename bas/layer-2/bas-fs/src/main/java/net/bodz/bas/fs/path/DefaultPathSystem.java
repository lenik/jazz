package net.bodz.bas.fs.path;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class DefaultPathSystem
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

    private IPath defaultContext;

    public DefaultPathSystem() {
        protocols = new HashMap<String, IPathResolver>();
        generics = new TreeSet<PathResolverKey>();

        String userDir = System.getProperty("user.dir");
        // defaultContext =
    }

    @Override
    public void setPathResolver(String protocol, IPathResolver resolver) {
        if (protocol == null)
            throw new NullPointerException("protocol");
        if (resolver == null)
            throw new NullPointerException("resolver");
        protocols.put(protocol, resolver);
    }

    @Override
    public void unsetPathResolver(String protocol) {
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
            throws PathException {
        if (path == null)
            throw new NullPointerException("path");
        int colon = path.indexOf(':');
        if (colon != -1) {
            String protocol = path.substring(0, colon);
            IPathResolver protocolResolver = protocols.get(protocol);
            if (protocolResolver != null) {
                String protocolSpecificPath = path.substring(colon + 1);
                return protocolResolver.resolve(protocolSpecificPath);
            }
        }

        for (PathResolverKey key : generics) {
            IPath resolved = key.resolver.resolve(path);
            if (resolved != null)
                return resolved;
        }

        IPath context = getDefaultContext();
        return context.resolve(path);
    }

    @Override
    public IPath getDefaultContext() {
        if (defaultContext == null)
            throw new IllegalStateException("Default context wasn't set");
        return defaultContext;
    }

    public void setDefaultBase(IPath defaultContext) {
        if (defaultContext == null)
            throw new NullPointerException("defaultContext");
        this.defaultContext = defaultContext;
    }

}
