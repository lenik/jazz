package net.bodz.bas.program.skel;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.diff.DiffEntry;
import net.bodz.bas.text.diff.IDiffComparator;
import net.bodz.bas.text.diff.IDiffFormat;
import net.bodz.bas.vfs.IFile;

public class DiffPrinter {

    IDiffComparator comparator;
    IDiffFormat format = IDiffFormat.SIMPLE;
    IPrintOut out = Stdio.cout;

    int printCount;

    public DiffPrinter(IDiffComparator comparator, IDiffFormat format, IPrintOut out) {
        this.comparator = comparator;
        this.format = format;
        this.out = out;
    }

    public boolean printDiff3(String name, IFile file1, IFile file2, IFile file3)
            throws IOException {
        printCount++;
        boolean diff12 = _printDiff(name, file1, file2);
        boolean diff23 = _printDiff(name, file2, file3);
        return diff12 || diff23;
    }

    /**
     * Print differences between two files.
     * 
     * @return <code>true</code> if the two files are different, <code>false</code> if they are
     *         same.
     */
    public boolean printDiff(String name, IFile file1, IFile file2)
            throws IOException {
        printCount++;
        return _printDiff(name, file1, file2);
    }

    boolean _printDiff(String name, IFile file1, IFile file2)
            throws IOException {

        if (file1.exists() && !file2.exists()) {
            out.println("Deleted: " + name);
            return true;
        } else if (!file1.exists() && file2.exists()) {
            out.println("Created: ", name);
            return true;
        }

        if (comparator == null) {
            boolean equals = FileDiff.equals(file1.getInputSource(), file2.getInputSource());
            if (equals)
                ;
            else
                out.println("Modified: ", file1);
            return !equals;
        }

        List<String> lines1 = file1.getInputSource().tooling()._for(StreamReading.class).readLines();
        List<String> lines2 = file2.getInputSource().tooling()._for(StreamReading.class).readLines();

        List<DiffEntry> diffs = comparator.compareDiff(lines1, lines2);

        if (diffs.isEmpty())
            return false;

        out.println("Modified: ", file1);
        format.printDiffs(out, lines1, lines2, diffs);
        return true;
    }

    public int getPrintCount() {
        return printCount;
    }

}
