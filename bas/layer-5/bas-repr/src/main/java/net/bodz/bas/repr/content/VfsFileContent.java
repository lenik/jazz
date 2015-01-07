package net.bodz.bas.repr.content;

import net.bodz.bas.vfs.IFile;

public class VfsFileContent
        extends MutableContent {

    private IFile file;

    public VfsFileContent(IFile file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public IFile getFile() {
        return file;
    }

    public void setFile(IFile file) {
        this.file = file;
    }

    @Override
    public long getCreationTime() {
        return file.getAttributes().creationTime().toMillis();
    }

    @Override
    public long getLastModified() {
        return file.getAttributes().lastModifiedTime().toMillis();
    }

}
