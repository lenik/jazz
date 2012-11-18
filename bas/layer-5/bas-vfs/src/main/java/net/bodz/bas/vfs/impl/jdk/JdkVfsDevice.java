package net.bodz.bas.vfs.impl.jdk;

import java.io.File;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.IPath;

/**
 * Represent a drive (if exist).
 */
public class JdkVfsDevice
        extends AbstractVfsDevice {

    private JdkFile rootFile;

    /**
     * @param driveName
     *            Drive name. <code>null</code> for linux root, or [a-z] for DOS drives.
     */
    public JdkVfsDevice(JdkVfsDriver driver, String driveName) {
        super(driver, driver.protocol, driveName);
        File root = new File(driveName + "/");
        this.rootFile = new JdkFile(this, root);
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
    public JdkFile getRootFile() {
        return rootFile;
    }

    @Override
    public JdkPath parse(String localPath) {
        return new JdkPath(getProtocol(), getDriveName(), localPath);
    }

    @Override
    public JdkFile resolve(String localPath) {
        String jdkPath;
        String driveName = getDriveName();
        if (driveName == null)
            jdkPath = "/" + localPath;
        else
            jdkPath = driveName + ":/" + localPath;
        return new JdkFile(jdkPath);
    }

    @Override
    public JdkFile resolve(IPath _path)
            throws FileResolveException {
        return resolve(_path.getLocalPath());
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo) {
        File fileFrom = new File(localPathFrom);
        File fileTo = new File(localPathTo);
        return fileFrom.renameTo(fileTo);
    }

}
