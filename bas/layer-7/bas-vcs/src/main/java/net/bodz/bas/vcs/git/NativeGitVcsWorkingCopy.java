package net.bodz.bas.vcs.git;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.vcs.AbstractVcsWorkingCopy;
import net.bodz.bas.vcs.IVcsLogEntry;
import net.bodz.bas.vcs.VcsLogOptions;

public class NativeGitVcsWorkingCopy
        extends AbstractVcsWorkingCopy {

    static final Logger logger = LoggerFactory.getLogger(NativeGitVcsWorkingCopy.class);

    public NativeGitVcsWorkingCopy(File directory) {
        super(directory);
    }

    @Override
    public Iterable<IVcsLogEntry> log(String name, VcsLogOptions options)
            throws IOException, InterruptedException {
        File file = new File(getDirectory(), name);
        if (!file.exists())
            logger.warn("Non-existing file: " + file);

        List<String> cmdl = new ArrayList<String>();

        /**
         * git log [--follow] name
         */
        cmdl.add("git");
        cmdl.add("log");
        cmdl.add("--date=raw");

        if (options.followRenames)
            cmdl.add("--follow");

        if (options.includeChanges)
            cmdl.add("--name-status");

        if (options.maxEntries > 0)
            cmdl.add("--max-count=" + options.maxEntries);

        if (options.fromEntry != null)
            cmdl.add(options.fromEntry.getVersion());

        cmdl.add(name);

        String[] cmdv = cmdl.toArray(new String[0]);
        // logger.debug("Execute: ", StringArray.join(" ", cmdv));

        Process process = Runtime.getRuntime().exec(cmdv, null, getDirectory());
        String result = Processes.iocap(process, Charset.defaultCharset());
        Iterable<String> lines = new StringSource(result).to(StreamReading.class).lines();
        return NativeGitLogParser.parse(this, lines, options);
    }

    @Override
    public Iterable<String> getDiff(String name, String version)
            throws IOException, InterruptedException {
        File file = new File(getDirectory(), name);
        if (!file.exists())
            logger.warn("Non-existing file: " + file);

        List<String> cmdl = new ArrayList<String>();

        /**
         * git log [--follow] name
         */
        cmdl.add("git");
        cmdl.add("show");
        cmdl.add(version);
        cmdl.add("--");
        cmdl.add(name);

        String[] cmdv = cmdl.toArray(new String[0]);
        // logger.debug("Execute: ", StringArray.join(" ", cmdv));

        Process process = Runtime.getRuntime().exec(cmdv, null, getDirectory());
        String result = Processes.iocap(process, Charset.defaultCharset());
        return new StringSource(result).to(StreamReading.class).lines();
    }

}
