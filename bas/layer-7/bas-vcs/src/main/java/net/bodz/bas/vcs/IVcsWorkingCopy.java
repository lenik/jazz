package net.bodz.bas.vcs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface IVcsWorkingCopy {

    Path getDirectory();

    Iterable<IVcsLogEntry> log(String name, VcsLogOptions options)
            throws IOException, InterruptedException;

    Iterable<String> getDiff(String name, String version)
            throws IOException, InterruptedException;

}
