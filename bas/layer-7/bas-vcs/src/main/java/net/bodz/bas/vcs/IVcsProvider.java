package net.bodz.bas.vcs;

import java.nio.file.Path;

public interface IVcsProvider {

    boolean isWorkingCopy(Path workDir);

    IVcsWorkingCopy getWorkingCopy(Path workDir);

}
