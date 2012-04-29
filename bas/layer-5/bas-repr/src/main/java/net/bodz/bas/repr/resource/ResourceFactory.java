package net.bodz.bas.repr.resource;

public abstract class ResourceFactory {

    /**
     * Get the resource type name which will be used to prefix the url.
     * 
     * @return The resource type name.
     */
    public abstract String getType();

    /**
     * Resolve the resource.
     * 
     * @param path
     *            Non-<code>null</code> path, without the leading slash.
     * @return <code>null</code> if couldn't resolve the path.
     */
    public abstract IResource resolve(String path)
            throws ResourceResolveException;

}
