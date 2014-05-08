package net.bodz.bas.vcs;

import java.io.File;

public abstract class AbstractVcsProvider
        implements IVcsProvider {

    @Override
    public IVcsWorkingCopy getWorkingCopy(File workDir) {
        if (!isWorkingCopy(workDir))
            throw new IllegalArgumentException("Not a working copy: " + workDir);
        return newWorkingCopy(workDir);
    }

    protected abstract IVcsWorkingCopy newWorkingCopy(File workDir);

}
