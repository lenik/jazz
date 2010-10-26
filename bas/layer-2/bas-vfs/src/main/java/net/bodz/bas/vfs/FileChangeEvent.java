package net.bodz.bas.vfs;

import java.util.EventObject;


/**
 * @see org.apache.commons.vfs.FileChangeEvent
 */
public class FileChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    private final IFile file;

    public FileChangeEvent(IFile file) {
        super(file);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public IFile getFile() {
        return file;
    }

    @Override
    public String toString() {
        return file.toString();
    }

}
