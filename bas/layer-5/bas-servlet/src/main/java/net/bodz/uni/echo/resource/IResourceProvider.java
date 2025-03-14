package net.bodz.uni.echo.resource;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.bas.t.order.IPriority;

public interface IResourceProvider
        extends IPriority {

    /**
     * Get a system-wide accessible resource.
     *
     * @param path
     *            Non-null path string, without the leading "/".
     * @return <code>null</code> if the resource isn't existed.
     */
    @Nullable
    URL getResource(@NotNull String path);

    /**
     * Get resources with same path.
     *
     * @param path
     *            Non-null path string, without the leading "/".
     * @return List of matched resources.
     */
    @NotNull
    List<URL> getResources(@NotNull String path)
            throws IOException;

    /**
     * Get resources with same path.
     *
     * @param path
     *            Non-null path string, without the leading "/".
     * @return List of matched resources.
     */
    void findResources(@NotNull List<URL> resources, @NotNull String path)
            throws IOException;

}
