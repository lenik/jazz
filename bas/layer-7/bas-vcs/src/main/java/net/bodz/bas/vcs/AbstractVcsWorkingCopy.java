package net.bodz.bas.vcs;

import java.nio.file.Path;

public abstract class AbstractVcsWorkingCopy
        implements IVcsWorkingCopy {

    private Path directory;

    public AbstractVcsWorkingCopy(Path directory) {
        if (directory == null)
            throw new NullPointerException("directory");
        this.directory = directory;
    }

    @Override
    public Path getDirectory() {
        return directory;
    }

}
