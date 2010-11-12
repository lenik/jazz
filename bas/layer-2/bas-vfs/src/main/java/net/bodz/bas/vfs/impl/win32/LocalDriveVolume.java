package net.bodz.bas.vfs.impl.win32;

import net.bodz.bas.vfs.AbstractVolume;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathException;

public class LocalDriveVolume
        extends AbstractVolume {

    private final char driveLetter;

    public LocalDriveVolume(char driveLetter)
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
    public IPath resolve(String localPath) {
        return null;
    }

    @Override
    public IFile resolveFile(String localPath) {
        return null;
    }

    /**
     * @param containerPath
     *            in format of <code>`X:'</code>
     */
//    @Override
    public IVolume getVolume(String containerPath)
            throws PathException {
        assert containerPath != null;
        if (containerPath.length() != 1)
            throw new PathException("Bad container-path: " + containerPath);
        char drive = containerPath.charAt(0);
        LocalDriveVolume volume = drives.get(drive);
        if (volume == null) {
            volume = new LocalDriveVolume(drive);
            drives.put(drive, volume);
        }
        return volume;
    }

}
