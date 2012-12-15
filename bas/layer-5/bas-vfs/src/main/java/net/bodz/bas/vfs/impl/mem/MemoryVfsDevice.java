package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.inode.Inode;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class MemoryVfsDevice
        extends AbstractVfsDevice {

    Inode rootInode;
    MemoryFile rootFile;

    public MemoryVfsDevice(MemoryVfsDriver driver, String scopeName) {
        super(driver, driver.protocol, scopeName);
        this.rootInode = new Inode(null);

        MemoryPath rootPath = parse("");
        this.rootFile = new MemoryFile(this, rootPath);
    }

    public String getScopeName() {
        return getDeviceSpec();
    }

    public Inode getRootInode() {
        return rootInode;
    }

    @Override
    public MemoryFile getRootFile() {
        return rootFile;
    }

    @Override
    public MemoryPath parse(String localPath)
            throws BadPathException {
        return new MemoryPath(getProtocol(), getScopeName(), localPath);
    }

    @Override
    public MemoryFile resolve(IPath _path)
            throws FileResolveException {
        MemoryPath path = (MemoryPath) _path;
        return new MemoryFile(this, path);
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo)
            throws BadPathException {
        MemoryFile file = (MemoryFile) resolve(localPathFrom);
        return file.renameTo(localPathTo);
    }

}
