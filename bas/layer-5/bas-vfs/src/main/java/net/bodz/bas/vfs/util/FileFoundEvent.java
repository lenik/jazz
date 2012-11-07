package net.bodz.bas.vfs.util;

import java.util.EventObject;

import net.bodz.bas.vfs.IFile;

public class FileFoundEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    IFile file;
    boolean excluded;
    FileFinder.RecIter iterator;

    public FileFoundEvent(FileFinder.RecIter finderIter, IFile file) {
        super(finderIter.getFinder());
        this.iterator = finderIter;
        this.file = file;
    }

    public IFile getFile() {
        return file;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean isCanceled() {
        return iterator.canceled;
    }

    public void setCanceled(boolean canceled) {
        iterator.canceled = canceled;
    }

}
