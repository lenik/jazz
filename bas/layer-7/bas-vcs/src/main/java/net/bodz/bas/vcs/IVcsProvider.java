package net.bodz.bas.vcs;

import java.io.File;

public interface IVcsProvider {

    boolean isWorkingCopy(File workDir);

    IVcsWorkingCopy getWorkingCopy(File workDir);

}
