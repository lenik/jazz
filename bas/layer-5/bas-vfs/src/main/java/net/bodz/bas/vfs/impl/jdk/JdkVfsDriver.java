package net.bodz.bas.vfs.impl.jdk;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class JdkVfsDriver
        extends AbstractVfsDriver {

    String protocol;
    JdkVfsDevice superDrive;
    Map<String, JdkVfsDevice> driveMap = new HashMap<>();

    public JdkVfsDriver(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    @Override
    protected JdkPath _parse(String protocol, String _path)
            throws BadPathException {
        // Ignore the protocol.
        int colon = _path.indexOf(':');
        String drive;
        String localPath;
        if (colon != -1) {
            drive = _path.substring(0, colon);
            localPath = _path.substring(colon + 1);
            // if (drive.length() == 0) drive = null;
        } else {
            drive = null;
            localPath = _path;
        }

        JdkVfsDevice device = getDrive(drive);
        return device.parse(localPath);
    }

    @Override
    public JdkFile resolve(IPath _path)
            throws FileResolveException {
        JdkPath path = (JdkPath) _path;
        String driveName = path.getDeviceSpec();
        JdkVfsDevice drive = getDrive(driveName);
        JdkFile file = drive.resolve(path.getLocalPath());
        return file;
    }

    public List<JdkVfsDevice> getDrives() {
        List<JdkVfsDevice> drives = new ArrayList<JdkVfsDevice>();

        for (File root : File.listRoots()) {
            String deviceName = getDriveName(root);
            JdkVfsDevice drive = getDrive(deviceName);

            JdkFile rootFile = drive.getRootFile();
            assert rootFile.getOrigFile().equals(root);

            drives.add(drive);
        }
        return drives;
    }

    public String getDriveName(File file) {
        String pathname = file.getPath();
        int colon = pathname.indexOf(':');
        if (colon == -1)
            return null;
        else
            return pathname.substring(0, colon);
    }

    public JdkVfsDevice getDrive(File jdkFile) {
        String deviceName = getDriveName(jdkFile);
        return getDrive(deviceName);
    }

    public synchronized JdkVfsDevice getDrive(String driveName) {
        if (driveName == null)
            return superDrive;

        JdkVfsDevice device = driveMap.get(driveName);
        if (device == null) {
            File rootJdkFile = new File(driveName + "/");
            device = new JdkVfsDevice(this, rootJdkFile);
            driveMap.put(driveName, device);
        }
        return device;
    }

    private static final JdkVfsDriver instance = new JdkVfsDriver("file");

    public static JdkVfsDriver getInstance() {
        return instance;
    }

}
