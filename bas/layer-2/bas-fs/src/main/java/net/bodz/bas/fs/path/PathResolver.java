package net.bodz.bas.fs.path;

import net.bodz.bas.fs.IFsEntry;

public class PathResolver
        implements IPathResolver {

    @Override
    public IPath resolve(String path)
            throws PathException {
        return null;
    }

}

interface IFileSystem {

    String getVolumnName();

    IFsEntry resolve(String path);

    IFsEntry resolve(IPath path);

    long getCapacity();

    long getFreeSize();

}