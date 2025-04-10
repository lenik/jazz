package net.bodz.bas.vcs.git;

import java.nio.file.Files;
import java.nio.file.Path;

import net.bodz.bas.vcs.AbstractVcsProvider;
import net.bodz.bas.vcs.IVcsWorkingCopy;

public class NativeGitVcsProvider
        extends AbstractVcsProvider {

    @Override
    public boolean isWorkingCopy(Path workDir) {
        Path gitDir = workDir.resolve(".git");
        return Files.exists(gitDir);
    }

    @Override
    protected IVcsWorkingCopy newWorkingCopy(Path workDir) {
        return new NativeGitVcsWorkingCopy(workDir);
    }

}
