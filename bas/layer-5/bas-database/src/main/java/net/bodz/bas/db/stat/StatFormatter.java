package net.bodz.bas.db.stat;

import java.util.Map.Entry;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.string.TreeLineChars;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintOut;

public class StatFormatter {

    StatDumpFormat format = StatDumpFormat.csv;

    boolean treeGraph = true;
    TreeLineChars treeLineChars = TreeLineChars.smooth;

    boolean showTitle = true;
    boolean showUnits = false;

    boolean quoted = false;
    boolean removeZeroColumns = false;
    boolean convertZeroToSpace = false;

    public StatDumpFormat getFormat() {
        return format;
    }

    public void setFormat(StatDumpFormat format) {
        this.format = format;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public boolean isShowUnits() {
        return showUnits;
    }

    public void setShowUnits(boolean showUnits) {
        this.showUnits = showUnits;
    }

    public boolean isTreeGraph() {
        return treeGraph;
    }

    public void setTreeGraph(boolean treeGraph) {
        this.treeGraph = treeGraph;
    }

    public TreeLineChars getTreeLineChars() {
        return treeLineChars;
    }

    public void setTreeLineChars(TreeLineChars treeLineChars) {
        if (treeLineChars == null)
            throw new NullPointerException("treeLineChars");
        this.treeLineChars = treeLineChars;
    }

    public boolean isQuoted() {
        return quoted;
    }

    public void setQuoted(boolean quoted) {
        this.quoted = quoted;
    }

    public boolean isRemoveZeroColumns() {
        return removeZeroColumns;
    }

    public void setRemoveZeroColumns(boolean removeZeroColumns) {
        this.removeZeroColumns = removeZeroColumns;
    }

    public boolean isConvertZeroToSpace() {
        return convertZeroToSpace;
    }

    public void setConvertZeroToSpace(boolean convertZeroToSpace) {
        this.convertZeroToSpace = convertZeroToSpace;
    }

    public String format(StatNode root) {
        StatDump dump = new StatDump(root, this);

        int pathWidth;
        int[] fieldWidths;

        if (format == StatDumpFormat.table) {
            pathWidth = dump.getPrefixNameWidth();
            fieldWidths = dump.getFieldWidths(showTitle);
        } else {
            pathWidth = 0;
            fieldWidths = new int[dump.fieldCount];
        }

        BCharOut out = new BCharOut();

        if (showTitle) {
            cell_(out, "Path", pathWidth, false);

            int fieldIndex = 0;
            for (Entry<String, ICounterDef<?>> entry : dump.usedCounterDefs.entrySet()) {
                String counterName = entry.getKey();
                cell(out, counterName, fieldWidths[fieldIndex], false);
                if (fieldIndex < dump.fieldCount - 1)
                    sep(out);
                else
                    out.println();
                fieldIndex++;
            }
        }

        for (StatDumpLine line : dump.lines) {
            String path;
            if (format == StatDumpFormat.csv)
                if (line.prefix.isEmpty())
                    path = line.name;
                else
                    path = line.prefix + "/" + line.name;
            else
                path = line.prefix + line.name;

            if (path.isEmpty())
                path = "(root)";

            cell_(out, path, pathWidth, false);

            for (int i = 0; i < line.fields.length; i++) {
                StatDumpField field = line.fields[i];
                String fieldStr = field == null ? "" : field.value;
                cell(out, fieldStr, fieldWidths[i], true);
                if (i < line.fields.length - 1)
                    sep(out);
                else
                    out.println();
            }
        }

        return out.toString();
    }

    void cell(IPrintOut out, String str, int width, boolean isNumber) {
        if (format == StatDumpFormat.csv) {
            if (quoted && !isNumber)
                str = StringQuote.qq(str);
        } else {
            if (isNumber)
                str = Strings.padLeft(str, width);
            else
                str = Strings.padRight(str, width);
        }
        out.print(str);
    }

    void cell_(IPrintOut out, String str, int width, boolean isNumber) {
        cell(out, str, width, isNumber);
        sep(out);
    }

    void sep(IPrintOut out) {
        if (format == StatDumpFormat.csv)
            out.print(",");
        else
            out.print(" ");
    }

}
