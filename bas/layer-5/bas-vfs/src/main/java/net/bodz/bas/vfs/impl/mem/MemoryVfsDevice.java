package net.bodz.bas.vfs.impl.mem;

import java.io.IOException;
import java.nio.file.CopyOption;

import net.bodz.bas.c.java.nio.CreateOptions;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.MutableFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class MemoryVfsDevice
        extends AbstractVfsDevice {

    private MemoryFile rootFile;

    public MemoryVfsDevice(MemoryVfsDriver driver, String scopeName) {
        super(driver, driver.protocol, scopeName);

        MemoryPath rootPath = parse("");
        this.rootFile = new MemoryFile(this, rootPath);
    }

    public String getScopeName() {
        return getDeviceSpec();
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
    public MemoryFile _resolveNoRec(IPath _path)
            throws FileResolveException {
        MemoryPath path = (MemoryPath) _path;
        return new MemoryFile(this, path);
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options)
            throws BadPathException, IOException {
        MemoryFile src = (MemoryFile) resolve(localPathFrom);
        MemoryFile dest = (MemoryFile) resolve(localPathTo);

        if (!src.isExisted())
            return false;
        if (dest.isExisted())
            return false;

        boolean createParents = CreateOptions.isCreateParents(options);

        String destName = dest.getName();
        MemoryFile destParentFile = (MemoryFile) dest.getParentFile();

        if (!destParentFile.isExisted())
            if (createParents) {
                if (!destParentFile.mkdirs())
                    return false;
            } else
                return false;

        dest.detach();

        src.setName(destName);
        src.attach(destParentFile);
        return true;
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        MutableFile src = (MutableFile) resolve(localPath);
        return src.linkTo(target, symbolic);
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws IOException {
        IFile file = resolve(localPath);
        return file.readSymbolicLink();
    }

}
