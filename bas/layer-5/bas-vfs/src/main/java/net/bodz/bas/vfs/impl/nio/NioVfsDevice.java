package net.bodz.bas.vfs.impl.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.BadPathException;
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
    public NioVfsDevice(NioVfsDriver driver, String rootName, Path rootPath) {
        super(driver, driver.protocol, rootName);
        this.rootFile = new NioFile(this, //
                rootPath.getFileName() == null ? "" : rootPath.getFileName().toString(), // ""
                rootPath);
    }

    /**
     * Get the root name.
     * 
     * For Win32, the root name can be "C:", "D:", etc.
     * 
     * For *NIX, the root name should be "".
     * 
     * @return Non-<code>null</code> root name.
     */
    public String getRootName() {
        return getDeviceSpec();
    }

    @Override
    public NioFile getRootFile() {
        return rootFile;
    }

    @Override
    public NioPath parseLocalPath(String localPath) {
        return new NioPath(getProtocol(), getRootName(), localPath);
    }

    public Path resolvePath(String localPath) {
        String rootName = getRootName();
        String pathstr = rootName + "/" + localPath;
        Path path = Paths.get(pathstr);
        return path;
    }

    @Override
    public NioFile _resolveNoRec(String localPath) {
        Path path = resolvePath(localPath);
        return new NioFile(path);
    }

    @Override
    public NioFile _resolveNoRec(IPath _path)
            throws FileResolveException {
        return _resolveNoRec(_path.joinEntries());
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options)
            throws IOException {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        if (localPathFrom.equals(localPathTo))
            return true;

        Path pathFrom = resolvePath(localPathFrom);
        Path pathTo = resolvePath(localPathTo);

        Files.move(pathFrom, pathTo, options);
        return true;
    }

    @Override
    public boolean createLink(String _localPath, String targetSpec, boolean symbolic)
            throws IOException {
        NioPath localPath = parseLocalPath(_localPath);
        Path link = localPath.toPath();
        File linkFile = link.toFile();
        return FilePath.createLink(linkFile, targetSpec, symbolic);
    }

    @Override
    public String readSymbolicLink(String _localPath)
            throws BadPathException, IOException {
        NioPath localPath = parseLocalPath(_localPath);
        Path link = localPath.toPath();
        Path target = Files.readSymbolicLink(link);
        return target.toString();
    }

}
