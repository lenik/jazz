package net.bodz.bas.c.string;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.text.diff.DiffComparators;
import net.bodz.bas.text.diff.DiffEntry;
import net.bodz.bas.text.diff.IDiffComparator;
import net.bodz.bas.text.diff.IDiffFormat;

public class StringDiff {

    public static void diff(List<?> a, List<?> b, IPrintOut out, IDiffFormat format) {
        IDiffComparator alg = DiffComparators.gnudiff;
        List<DiffEntry> diffs = alg.compareDiff(a, b);
        format.printDiffs(out, a, b, diffs);
    }

    public static void diff(List<?> a, List<?> b, IPrintOut out) {
        diff(a, b, out, IDiffFormat.SIMPLE);
    }

    public static String diff(List<?> a, List<?> b) {
        BCharOut buffer = new BCharOut();
        diff(a, b, buffer);
        return buffer.toString();
    }

    public static void diff(String a, String b, IPrintOut out) {
        String[] av = a.split("\n");
        String[] bv = b.split("\n");
        diff(Arrays.asList(av), Arrays.asList(bv), out);
    }

    public static String diff(String a, String b) {
        BCharOut buffer = new BCharOut();
        diff(a, b, buffer);
        return buffer.toString();
    }

}
