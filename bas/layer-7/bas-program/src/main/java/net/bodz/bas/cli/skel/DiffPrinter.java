package net.bodz.bas.cli.skel;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.vfs.IFile;

public class DiffPrinter {

    Logger logger = LoggerFactory.getLogger(DiffPrinter.class);

    DiffComparator algorithm;
    DiffFormat format = DiffFormats.Simdiff;
    IPrintOut out = Stdio.cout;

    int printCount;

    public DiffPrinter(DiffComparator algorithm, DiffFormat format, IPrintOut out) {
        this.algorithm = algorithm;
        this.format = format;
        this.out = out;
    }

    public boolean printDiff3(IFile file1, IFile file2, IFile file3)
            throws IOException {
        printCount++;
        boolean diff12 = _printDiff(file1, file2);
        boolean diff23 = _printDiff(file2, file3);
        return diff12 || diff23;
    }

    /**
     * Print differences between two files.
     * 
     * @return <code>true</code> if the two files are different, <code>false</code> if they are
     *         same.
     */
    public boolean printDiff(IFile file1, IFile file2)
            throws IOException {
        printCount++;
        return _printDiff(file1, file2);
    }

    boolean _printDiff(IFile file1, IFile file2)
            throws IOException {

        if (file1.exists() && !file2.exists()) {
            logger.info("[new ]", file1);
            return true;
        } else if (!file1.exists() && file2.exists()) {
            logger.info("[miss]", file1);
            return true;
        }
        if (algorithm == null) {
            if (FileDiff.equals(file1.getInputSource(), file2.getInputSource()))
                return false;
            logger.info("[edit] ", file1);
            return true;
        }
        List<String> al = file1.getInputSource().tooling()._for(StreamReading.class).readLines();
        List<String> bl = file2.getInputSource().tooling()._for(StreamReading.class).readLines();
        List<DiffInfo> diffs = algorithm.diffCompare(al, bl);
        if (diffs.size() == 0)
            return false;
        logger.info("[edit] ", file1);
        format.format(al, bl, diffs, out);
        return true;
    }

    public int getPrintCount() {
        return printCount;
    }

}
