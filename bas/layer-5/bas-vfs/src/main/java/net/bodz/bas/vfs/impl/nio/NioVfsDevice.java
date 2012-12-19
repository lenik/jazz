package net.bodz.bas.vfs.impl.nio;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.IPath;

/**
 * Represent a drive (if exist).
 */
public class NioVfsDevice
        extends AbstractVfsDevice {

    private NioFile rootFile;

    /**
     * @param driveName
     *            Drive name. <code>null</code> for linux root, or [a-z] for DOS drives.
     */
    public NioVfsDevice(NioVfsDriver driver, String driveName) {
        super(driver, driver.protocol, driveName);
        File root = new File(driveName + "/");
        this.rootFile = new NioFile(this, root);
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
    public NioFile getRootFile() {
        return rootFile;
    }

    @Override
    public NioPath parse(String localPath) {
        return new NioPath(getProtocol(), getDriveName(), localPath);
    }

    @Override
    public NioFile resolve(String localPath) {
        String jdkPath;
        String driveName = getDriveName();
        if (driveName == null)
            jdkPath = "/" + localPath;
        else
            jdkPath = driveName + ":/" + localPath;
        return new NioFile(jdkPath);
    }

    @Override
    public NioFile resolve(IPath _path)
            throws FileResolveException {
        return resolve(_path.getLocalPath());
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo) {
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
