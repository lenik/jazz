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

    /**
     * The drive length is 1 char for windows drives.
     */
    public static final int MAX_DRIVE_LENGTH = 1;

    final String protocol;
    final JdkVfsDevice superDrive;
    final Map<String, JdkVfsDevice> driveMap;

    public JdkVfsDriver(String protocol) {
        this.protocol = protocol;
        superDrive = new JdkVfsDevice(this, null);
        driveMap = new HashMap<>();
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
        if (colon != -1 && colon <= MAX_DRIVE_LENGTH) {
            drive = _path.substring(0, colon);
            localPath = _path.substring(colon + 1);
            // if (drive.length() == 0) drive = null;
        } else {
            drive = null;
            localPath = _path;
        }

        while (localPath.startsWith("/"))
            localPath = localPath.substring(1);

        JdkVfsDevice device = getDrive(drive);
        return device.parse(localPath);
    }

    @Override
    public JdkFile resolve(IPath _path)
            throws FileResolveException {
        JdkPath path = (JdkPath) _path;
        String driveName = path.getDeviceSpec();
        JdkVfsDevice device = getDrive(driveName);
        JdkFile file = device.resolve(path.getLocalPath());
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
            device = new JdkVfsDevice(this, driveName);
            driveMap.put(driveName, device);
        }
        return device;
    }

    private static final JdkVfsDriver instance = new JdkVfsDriver("file");

    public static JdkVfsDriver getInstance() {
        return instance;
    }

}
