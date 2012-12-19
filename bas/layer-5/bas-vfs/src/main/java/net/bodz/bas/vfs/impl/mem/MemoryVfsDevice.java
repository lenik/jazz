package net.bodz.bas.vfs.impl.mem;

import java.io.IOException;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.Inode;

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
        MemoryFile src = (MemoryFile) resolve(localPathFrom);
        MemoryFile dest = (MemoryFile) resolve(localPathTo);

        if (!src.isExisted())
            return false;
        if (dest.isExisted())
            return false;

        String destName = dest.getName();
        MemoryFile destParentFile = (MemoryFile) dest.getParentFile();
        if (!destParentFile.mkdirs())
            return false;

        Inode srcNode = src.getInode();
        Inode destParentNode = destParentFile.getInode();
        srcNode.detach();
        srcNode.attach(destParentNode, destName);
        return true;
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        throw new NotImplementedException();
    }

}
