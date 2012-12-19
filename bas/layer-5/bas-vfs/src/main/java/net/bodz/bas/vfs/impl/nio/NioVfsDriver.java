package net.bodz.bas.vfs.impl.nio;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class NioVfsDriver
        extends AbstractVfsDriver {

    /**
     * The drive length is 1 char for windows drives.
     */
    public static final int MAX_DRIVE_LENGTH = 1;

    final String protocol;
    final Map<String, NioVfsDevice> driveMap;

    public NioVfsDriver(String protocol) {
        this.protocol = protocol;
        superDrive = new NioVfsDevice(this, null);
        driveMap = new HashMap<>();
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    @Override
    protected NioPath _parse(String protocol, String _path)
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

        NioVfsDevice device = getDrive(drive);
        return device.parse(localPath);
    }

    @Override
    public NioFile resolve(IPath _path)
            throws FileResolveException {
        NioPath path = (NioPath) _path;
        String driveName = path.getDeviceSpec();
        NioVfsDevice device = getDrive(driveName);
        NioFile file = device.resolve(path);
        return file;
    }

    public List<NioVfsDevice> getDrives() {
        List<NioVfsDevice> drives = new ArrayList<NioVfsDevice>();

        for (File root : File.listRoots()) {
            String deviceName = getDriveName(root);
            NioVfsDevice drive = getDrive(deviceName);

            NioFile rootFile = drive.getRootFile();
            assert rootFile.getOrigFile().equals(root);

            drives.add(drive);
        }
        return drives;
    }

    public String getDriveName(Path jdkPath) {
        String pathname = jdkPath.toString();
        int colon = pathname.indexOf(':');
        if (colon == -1)
            return null;
        else
            return pathname.substring(0, colon);
    }

    public NioVfsDevice getDrive(Path jdkPath) {
        String deviceName = getDriveName(jdkPath);
        return getDrive(deviceName);
    }

    public synchronized NioVfsDevice getDrive(String driveName) {
        if (driveName == null)
            return superDrive;

        NioVfsDevice device = driveMap.get(driveName);
        if (device == null) {
            device = new NioVfsDevice(this, driveName);
            driveMap.put(driveName, device);
        }
        return device;
    }

    private static final NioVfsDriver instance = new NioVfsDriver("file");

    public static NioVfsDriver getInstance() {
        return instance;
    }

}
