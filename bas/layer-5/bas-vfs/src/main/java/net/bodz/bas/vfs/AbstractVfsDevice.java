package net.bodz.bas.vfs;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.traits.EmptyAttributes;
import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractVfsDevice
        implements IVfsDevice {

    private final IVfsDriver driver;

    public AbstractVfsDevice(IVfsDriver driver) {
        if (driver == null)
            throw new NullPointerException("driver");
        this.driver = driver;
    }

    @Override
    public IVfsDriver getDriver() {
        return driver;
    }

    @Override
    public IFile getDeviceFile() {
        return null;
    }

    @Override
    public IAttributes getAttributes() {
        return EmptyAttributes.getInstance();
    }

    @Override
    public final List<? extends IPath> getRootPaths() {
        List<? extends IFile> rootFiles = getRootFiles();
        List<IPath> rootPaths = new ArrayList<IPath>(rootFiles.size());
        for (IFile rootFile : rootFiles)
            rootPaths.add(rootFile.getPath());
        return rootPaths;
    }

}
