package net.bodz.bas.vcs;

import java.io.File;

public abstract class AbstractVcsWorkingCopy
        implements IVcsWorkingCopy {

    private File directory;

    public AbstractVcsWorkingCopy(File directory) {
        if (directory == null)
            throw new NullPointerException("directory");
        this.directory = directory;
    }

    @Override
    public File getDirectory() {
        return directory;
    }

}
