package net.bodz.bas.vfs.impl.fake;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;

public class FakePath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    private IFile file;

    public FakePath(String localPath, IFile file) {
        super(localPath);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public IVolume getVolume() {
        return FakeVolume.getInstance();
    }

    @Override
    public IFile toFile() {
        return file;
    }

}
