package net.bodz.bas.vcs;

import java.nio.file.Path;

public abstract class AbstractVcsProvider
        implements IVcsProvider {

    @Override
    public IVcsWorkingCopy getWorkingCopy(Path workDir) {
        if (!isWorkingCopy(workDir))
            throw new IllegalArgumentException("Not a working copy: " + workDir);
        return newWorkingCopy(workDir);
    }

    protected abstract IVcsWorkingCopy newWorkingCopy(Path workDir);

}
