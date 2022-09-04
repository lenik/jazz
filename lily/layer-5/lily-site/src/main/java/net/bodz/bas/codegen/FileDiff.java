package net.bodz.bas.codegen;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.compare.dmp.*;
import net.bodz.bas.text.row.IRow;
import net.bodz.bas.text.row.IntegerRow;
import net.bodz.bas.text.row.StringRow;

public class FileDiff {

    static DMPConfig config = new DMPConfig();

    static CharsComparator charsComparator = new CharsComparator(config);
    static CsIntsComparator atomsComparator = new CsIntsComparator(config);
    static LinesComparator linesComparator = new LinesComparator(config);
    static TrimmedLinesComparator trimmedLinesComparator = new TrimmedLinesComparator(config);

    public static EditList<String> compareByLines(IRow<String> row1, IRow<String> row2)
            throws IOException {
        PackedRows<String> packedRows = new PackBuilder<String>(row1, row2).build();
        IntegerRow indexRow1 = packedRows.getIndexRow1();
        IntegerRow indexRow2 = packedRows.getIndexRow2();
        EditList<Integer> diffs = atomsComparator.compare(indexRow1, indexRow2);
        return packedRows.unpack(linesComparator, diffs);
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
        PackedRows<String> packedRows = new PackBuilder<String>(row1, row2).build();
        IntegerRow indexRow1 = packedRows.getIndexRow1();
        IntegerRow indexRow2 = packedRows.getIndexRow2();
        EditList<Integer> diffs = atomsComparator.compare(indexRow1, indexRow2);
        PatchList<Integer> patchList = diffs.createPatch(indexRow1);
        return packedRows.unpack(linesComparator, patchList);
    }

}
