package net.bodz.bas.codegen;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.compare.dmp.*;
import net.bodz.bas.text.row.IRow;
import net.bodz.bas.text.row.MutableRow;
import net.bodz.bas.text.row.StringRow;

public class FileDiff {

    static DMPConfig config = new DMPConfig();

    static CharsComparator charsComparator = new CharsComparator(config);
    static CsIntsComparator atomsComparator = new CsIntsComparator(config);
    static LinesComparator linesComparator = new LinesComparator(config);
    static TrimmedLinesComparator trimmedLinesComparator = new TrimmedLinesComparator(config);

    public static EditList<String> compareByLines(IRow<String> row1, IRow<String> row2)
            throws IOException {
        AtomMap<String> map = new AtomMap<String>();
        MutableRow<Integer> aRow1 = map.atomize(row1);
        MutableRow<Integer> aRow2 = map.atomize(row2);
        EditList<Integer> aDiffs = atomsComparator.compare(aRow1, aRow2);
        return map.restore(linesComparator, aDiffs);
    }

    public static EditList<String> compareByLines(List<String> lines1, List<String> lines2)
            throws IOException {
        StringRow row1 = new StringRow(lines1);
        StringRow row2 = new StringRow(lines2);
        return compareByLines(row1, row2);
    }

    public static PatchList<String> createPatchByLines(List<String> lines1, List<String> lines2)
            throws IOException {
        StringRow row1 = new StringRow(lines1);
        StringRow row2 = new StringRow(lines2);
        return createPatchByLines(row1, row2);
    }

    public static PatchList<String> createPatchByLines(IRow<String> row1, IRow<String> row2)
            throws IOException {
        AtomMap<String> map = new AtomMap<String>();
        MutableRow<Integer> aRow1 = map.atomize(row1);
        MutableRow<Integer> aRow2 = map.atomize(row2);
        EditList<Integer> aDiffs = atomsComparator.compare(aRow1, aRow2);
        PatchList<Integer> aPatchList = aDiffs.createPatch(aRow1);
        return map.restore(linesComparator, aPatchList);
    }

}
