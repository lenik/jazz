package net.bodz.bas.vfs.impl.pojf;

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

/**
 * POJF: Plain Old Java File
 */
public class PojfVfsDriver
        extends AbstractVfsDriver {

    /**
     * The drive length is 1 char for windows drives.
     */
    public static final int MAX_DRIVE_LENGTH = 1;

    final String protocol;
    final PojfVfsDevice superDrive;
    final Map<String, PojfVfsDevice> driveMap;

    public PojfVfsDriver(String protocol) {
        this.protocol = protocol;
        superDrive = new PojfVfsDevice(this, null);
        driveMap = new HashMap<>();
    }

    @Override
    public void configure(IFileSystem system) {
        system.addDriver(protocol, this);
    }

    @Override
    protected PojfPath _parse(String protocol, String _path)
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

        PojfVfsDevice device = getDrive(drive);
        return device.parse(localPath);
    }

    @Override
    public PojfFile resolve(IPath _path)
            throws FileResolveException {
        PojfPath path = (PojfPath) _path;
        String driveName = path.getDeviceSpec();
        PojfVfsDevice device = getDrive(driveName);
        PojfFile file = device.resolve(path);
        return file;
    }

    public List<PojfVfsDevice> getDrives() {
        List<PojfVfsDevice> drives = new ArrayList<PojfVfsDevice>();

        for (File root : File.listRoots()) {
            String deviceName = getDriveName(root);
            PojfVfsDevice drive = getDrive(deviceName);

            PojfFile rootFile = drive.getRootFile();
            assert rootFile.getInternalFile().equals(root);

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

    public PojfVfsDevice getDrive(File _file) {
        String deviceName = getDriveName(_file);
        return getDrive(deviceName);
    }

    public synchronized PojfVfsDevice getDrive(String driveName) {
        if (driveName == null)
            return superDrive;

        PojfVfsDevice device = driveMap.get(driveName);
        if (device == null) {
            device = new PojfVfsDevice(this, driveName);
            driveMap.put(driveName, device);
        }
        return device;
    }

    private static final PojfVfsDriver instance = new PojfVfsDriver("file");

    public static PojfVfsDriver getInstance() {
        return instance;
    }

}
