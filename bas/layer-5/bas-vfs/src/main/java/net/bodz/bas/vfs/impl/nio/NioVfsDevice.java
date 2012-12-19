package net.bodz.bas.vfs.impl.nio;

import java.io.File;

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

}
