package net.bodz.bas.vfs.impl.win32;

import net.bodz.bas.vfs.AbstractVolume;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class Win32Drive
        extends AbstractVolume {

    private final char driveLetter;
    private final IPath rootPath;

    Win32Drive(char driveLetter) {
        this.driveLetter = driveLetter;
        this.rootPath = new Win32Path(this, "");
    }

    public char getDriveLetter() {
        return driveLetter;
    }

    @Override
    public IPath getRootPath() {
        return rootPath;
    }

    @Override
    public IPath resolve(String localPath) {
        return new Win32Path(this, localPath);
    }

    @Override
    public IFile resolveFile(String localPath) {
        return new JavaioFile(driveLetter + ":/" + localPath);
    }

    @Override
    public String format(IPath path, PathFormat pathFormat) {
        String localPath = path.getLocalPath();
        while (localPath.startsWith("/"))
            localPath = localPath.substring(1);
        return driveLetter + ":/" + localPath;
    }

}
