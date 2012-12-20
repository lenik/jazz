package net.bodz.bas.vfs.impl.pojf;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;

import net.bodz.bas.err.NotImplementedException;
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
    public PojfPath parse(String localPath) {
        return new PojfPath(getProtocol(), getDriveName(), localPath);
    }

    @Override
    public PojfFile resolve(String localPath) {
        String jdkPath;
        String driveName = getDriveName();
        if (driveName == null)
            jdkPath = "/" + localPath;
        else
            jdkPath = driveName + ":/" + localPath;
        return new PojfFile(jdkPath);
    }

    @Override
    public PojfFile resolve(IPath _path)
            throws FileResolveException {
        return resolve(_path.getLocalPath());
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options) {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        if (localPathFrom.equals(localPathTo))
            return true;

        File fileFrom = new File(localPathFrom);
        File fileTo = new File(localPathTo);

        return fileFrom.renameTo(fileTo);
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        // java.nio.file.Files.createSymbolicLink(link, target, attrs);
        throw new NotImplementedException();
    }

}
