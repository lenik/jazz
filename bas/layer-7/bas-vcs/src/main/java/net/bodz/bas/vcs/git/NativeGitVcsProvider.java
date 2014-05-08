package net.bodz.bas.vcs.git;

import java.io.File;

import net.bodz.bas.vcs.AbstractVcsProvider;
import net.bodz.bas.vcs.IVcsWorkingCopy;

public class NativeGitVcsProvider
        extends AbstractVcsProvider {

    @Override
    public boolean isWorkingCopy(File workDir) {
        File gitDir = new File(workDir, ".git");
        return gitDir.exists();
    }

    @Override
    protected IVcsWorkingCopy newWorkingCopy(File workDir) {
        return new NativeGitVcsWorkingCopy(workDir);
    }

}
