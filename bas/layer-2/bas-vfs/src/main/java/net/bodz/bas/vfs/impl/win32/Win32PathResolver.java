package net.bodz.bas.vfs.impl.win32;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.vfs.path.AbstractPathResolver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class Win32PathResolver
        extends AbstractPathResolver {

    /**
     * Win32 indeed allows drive letter other then A-Z.
     * <p>
     * However, those drives are hidden and not very compatible.
     */
    static final boolean allowSpecialLetters = false;

    private Map<Character, Win32Drive> drives;

    public Win32PathResolver() {
        drives = new HashMap<Character, Win32Drive>();
    }

    @Override
    public boolean accepts(String protocol) {
        return protocol.length() == 1;
    }

    @Override
    public IPath resolve(String path)
            throws BadPathException {
        if (path.isEmpty())
            throw new BadPathException("empty path");

        // Win32 path doesn't allow char-escape, so it's safe to translate all the slashes.
        path = path.replace('\\', '/');

        int colon = path.indexOf(':');
        if (colon == -1)
            throw new BadPathException("No drive letter in the path", path);
        if (colon != 1)
            throw new BadPathException("The driver letter must be single character", path);

        String localPath = path.substring(2);

        char driveLetter = Character.toUpperCase(path.charAt(0));
        if (!allowSpecialLetters)
            if (!Character.isLetterOrDigit(driveLetter))
                throw new BadPathException("Invalid drive letter: " + driveLetter);

        Win32Drive drive;
        synchronized (drives) {
            drive = drives.get(driveLetter);
            if (drive == null) {
                drive = new Win32Drive(driveLetter);
                drives.put(driveLetter, drive);
            }
        }
        return drive.resolve(localPath);
    }

}
