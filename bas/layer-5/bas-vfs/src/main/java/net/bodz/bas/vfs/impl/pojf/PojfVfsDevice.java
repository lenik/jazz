package net.bodz.bas.vfs.impl.pojf;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.NotLinkException;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.IPath;

/**
 * Represent a drive (if exist).
 */
public class PojfVfsDevice
        extends AbstractVfsDevice {

    private PojfFile rootFile;

    /**
     * @param driveName
     *            Drive name. <code>null</code> for linux root, or [a-z] for DOS drives.
     */
    public PojfVfsDevice(PojfVfsDriver driver, String driveName) {
        super(driver, driver.protocol, driveName);
        File root = new File(driveName + "/");
        this.rootFile = new PojfFile(this, root);
    }

    /**
     * Get the name of the drive.
     * 
     * For DOS drives, the drive name contains the colon(:).
     * 
     * For *NIX, the drive name is an empty string.
     * 
     * @return Non-<code>null</code> drive name.
     */
    public String getDriveName() {
        return getDeviceSpec();
    }

    @Override
    public PojfFile getRootFile() {
        return rootFile;
    }

    @Override
    public PojfPath parseLocalPath(String localPath) {
        return new PojfPath(getProtocol(), getDriveName(), localPath);
    }

    public String _parse(String localPath) {
        String _pathstr;
        String driveName = getDriveName();
        if (driveName == null)
            _pathstr = "/" + localPath;
        else
            _pathstr = driveName + ":/" + localPath;
        return _pathstr;
    }

    public File _resolve(String localPath) {
        String _path = _parse(localPath);
        return new File(_path);
    }

    @Override
    public PojfFile _resolveNoRec(String localPath) {
        File file = _resolve(localPath);
        return new PojfFile(this, file);
    }

    @Override
    public PojfFile _resolveNoRec(IPath _path)
            throws FileResolveException {
        return _resolveNoRec(_path.getLocalPath());
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options) {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        if (localPathFrom.equals(localPathTo))
            return true;

        File fileFrom = _resolve(localPathFrom);
        File fileTo = _resolve(localPathTo);

        return fileFrom.renameTo(fileTo);
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {

        File _linkFile = _resolve(localPath);
        if (_linkFile.exists())
            return false;

        Path _link = _linkFile.toPath();

        Path _target = Paths.get(target);

        if (symbolic)
            Files.createSymbolicLink(_link, _target);
        else
            Files.createLink(_link, _target);
        return true;
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws NotLinkException, IOException {
        File _linkFile = _resolve(localPath);
        return FilePath.readSymbolicLink(_linkFile);
    }

}
