package net.bodz.bas.vfs.impl.jdk;

import java.io.File;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class JdkVfsDevice
        extends AbstractVfsDevice {

    private JdkFile rootFile;

    /**
     * The root name maybe: "" (for /), "c:" (for c:/).
     */
    public JdkVfsDevice(JdkVfsDriver driver, File rootFile) {
        super(driver, driver.protocol, rootFile.getName());
        this.rootFile = new JdkFile(this, rootFile);
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
        return new JdkPath(getProtocol(), localPath);
    }

    @Override
    public JdkFile resolve(String localPath) {
        return new JdkFile(localPath);
    }

    @Override
    public JdkFile resolve(IPath _path)
            throws FileResolveException {
        return resolve(_path.getLocalPath());
    }

    @Override
    public String format(String localPath, PathFormat pathFormat) {
        return localPath;
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo) {
        File fileFrom = new File(localPathFrom);
        File fileTo = new File(localPathTo);
        return fileFrom.renameTo(fileTo);
    }

}
