package net.bodz.bas.sysctx;

import java.io.File;

public interface IWorkingDirectoryContext {

    File getWorkingDirectory();

    void setWorkingDirectory(File workingDirectory);

}
