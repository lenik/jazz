package net.bodz.bas.vfs.impl.fake;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.DefaultPath;

public class FakePath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    private IFile file;

    public FakePath(String localPath, IFile file) {
        super(FakeFileSystem.getInstance(), localPath);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public IFile toFile() {
        return file;
    }

}
