package net.bodz.bas.vfs.impl.win32;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.path.AbstractPathResolver;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathException;

public class Win32PathResolver
        extends AbstractPathResolver {

    private Map<Character, LocalDriveVolume> drives;

    public Win32PathResolver() {
        drives = new HashMap<Character, LocalDriveVolume>();
    }

    @Override
    public boolean accepts(String protocol) {
        return protocol.length() == 1;
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
