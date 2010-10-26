package net.bodz.bas.vfs.impl.win32;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.IFileContainer;
import net.bodz.bas.vfs.path.AbstractPathResolver;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathException;

public class Win32PathResolver
        extends AbstractPathResolver {

    private Map<Character, Win32FileContainer> drives;

    public Win32PathResolver() {
        drives = new HashMap<Character, Win32FileContainer>();
    }

    @Override
    public boolean accepts(String protocol) {
        return protocol.length() == 1;
    }

    /**
     * @param containerPath
     *            in format of <code>`X:'</code>
     */
    @Override
    public IFileContainer getFileContainer(String containerPath)
            throws PathException {
        assert containerPath != null;
        if (containerPath.length() != 1)
            throw new PathException("Bad container-path: " + containerPath);
        char drive = containerPath.charAt(0);
        Win32FileContainer driveContainer = drives.get(drive);
        if (driveContainer == null) {
            driveContainer = new Win32FileContainer();
            drives.put(drive, driveContainer);
        }
        return driveContainer;
    }

    @Override
    public IPath resolve(String path)
            throws PathException {
        if (path.isEmpty())
            throw new PathException("empty path");

        // Win32 path doesn't allow char-escape, so it's safe to translate all the slashes.
        path = path.replace('\\', '/');

        return null;
    }

}
