package net.bodz.bas.html.io.mod.table;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.tag.HtmlTable;
import net.bodz.bas.io.ITreeOut;

public class HtmlDocForModTable
        extends HtmlDoc {

    List<ModTable> tables = new ArrayList<>();

    public HtmlDocForModTable(ITreeOut treeOut, HtmlOutputFormat outputFormat) {
        super(treeOut, outputFormat);
    }

    public HtmlDocForModTable(Writer writer, HtmlOutputFormat outputFormat) {
        super(writer, outputFormat);
    }

    @Override
    public HtmlTable table() {
        ModTable table = new ModTable(this);
        tables.add(table);
        return table;
    }

    public List<ModTable> getTables() {
        return tables;
    }

}
