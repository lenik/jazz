package net.bodz.bas.vcs;

import java.io.File;
import java.io.IOException;

public interface IVcsWorkingCopy {

    File getDirectory();

    Iterable<IVcsLogEntry> log(String name, VcsLogOptions options)
            throws IOException, InterruptedException;

}
