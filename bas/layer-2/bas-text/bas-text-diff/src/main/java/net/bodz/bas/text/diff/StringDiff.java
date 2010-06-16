package net.bodz.bas.text.diff;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintCharOut;

public class StringDiff {

    public static void diff(List<?> a, List<?> b, IPrintCharOut out, DiffFormat format) {
        DiffComparator alg = DiffComparators.gnudiff;
        List<DiffInfo> diffs = alg.diffCompare(a, b);
        format.format(a, b, diffs, out);
    }

    public static void diff(List<?> a, List<?> b, IPrintCharOut out) {
        diff(a, b, out, DiffFormats.Simdiff);
    }

    public static String diff(List<?> a, List<?> b) {
        BCharOut buffer = new BCharOut();
        diff(a, b, buffer);
        return buffer.toString();
    }

    public static void diff(String a, String b, IPrintCharOut out) {
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
