package net.bodz.bas.vfs.impl.win32;

import net.bodz.bas.vfs.AbstractFileContainer;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathException;

public class Win32FileContainer
        extends AbstractFileContainer {

    private final char driveLetter;

    public Win32FileContainer(char driveLetter)
            throws PathException {
        super(null);
        if (!Character.isLetterOrDigit(driveLetter))
            throw new PathException("Invalid drive letter: " + driveLetter);
        this.driveLetter = driveLetter;
    }

    public char getDriveLetter() {
        return driveLetter;
    }

    @Override
    public IPath getRootPath() {
        return null;
    }

    @Override
    public IFile get(IPath path) {
        return null;
    }

    @Override
    public IPath resolve(String containerSpecificPath) {
        return null;
    }

}
