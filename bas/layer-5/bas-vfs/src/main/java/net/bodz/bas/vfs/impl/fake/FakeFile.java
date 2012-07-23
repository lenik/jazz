package net.bodz.bas.vfs.impl.fake;

import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class FakeFile
        extends AbstractFile.TransientPath {

    private long creationTime;
    private long lastModifiedTime;

    public FakeFile(String pathString) {
        super(pathString);
        creationTime = lastModifiedTime = System.currentTimeMillis();
    }

    @Override
    public IFileSystem getFileSystem() {
        return FakeFileSystem.getInstance();
    }

    @Override
    protected IPath constructPath(String pathString)
            throws BadPathException {
        return new FakePath(pathString, this);
    }

    @Override
    protected FakeFile populate(Object obj) {
        super.populate(obj);
        if (obj instanceof FakeFile) {
            FakeFile o = (FakeFile) obj;
            this.creationTime = o.creationTime;
            this.lastModifiedTime = o.lastModifiedTime;
        }
        return this;
    }

    @Override
    public Boolean exists() {
        return true;
    }

    @Override
    public boolean isBlob() {
        return true;
    }

    @Override
    public Long getCreationTime() {
        return creationTime;
    }

    @Override
    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    @Override
    public boolean setLastModifiedTime(long date) {
        this.lastModifiedTime = date;
        return true;
    }

}
