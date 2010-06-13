package net.bodz.bas.fs.path;

import net.bodz.bas.fs.IFileContainer;
import net.bodz.bas.fs.RelativeFileContainer;

public abstract class AbstractPathResolver
        implements IPathResolver {

    /**
     * The default implementation treats string-before(path, ':') <i>(excludes the last colon(':')
     * )</i> as the container-prefix.
     * 
     * @return 0 if char <code>':'</code> isn't found in the <code>path</code>.
     */
    @Override
    public int getPrefixLength(String path) {
        int lastColon = path.lastIndexOf(':');
        if (lastColon == -1)
            return 0;
        return lastColon;
    }

    @Override
    public IPath resolve(String path)
            throws PathException {
        int prefixLength = getPrefixLength(path);
        IFileContainer container;
        if (prefixLength == 0)
            container = RelativeFileContainer.getInstance();
        else {
            String containerPath = path.substring(0, prefixLength);
            container = getFileContainer(containerPath);
        }

        String containerSpecificPath = path.substring(prefixLength);
        IPath resolvedPath = container.resolve(containerSpecificPath);
        return resolvedPath;
    }

}
