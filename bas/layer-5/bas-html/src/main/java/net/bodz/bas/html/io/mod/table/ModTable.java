package net.bodz.bas.html.io.mod.table;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlTable;
import net.bodz.bas.html.io.tag.HtmlTfoot;
import net.bodz.bas.html.io.tag.HtmlThead;

/**
 * HtmlTable for modeling
 */
public class ModTable
        extends HtmlTable {

    DataRow headRow;
    List<DataRow> rows;
    DataRow footRow;

    public ModTable(HtmlDoc doc) {
        super(doc);
        headRow = new DataRow();
        rows = new ArrayList<>();
        footRow = new DataRow();
    }

    @Override
    public HtmlThead thead() {
        return begin("thead", new ModThead(doc, headRow));
    }

    @Override
    public HtmlTfoot tfoot() {
        return begin("tfoot", new ModTfoot(doc, footRow));
    }

    @Override
    public ModTbody tbody() {
        return begin("tbody", new ModTbody(doc, rows));
    }

    public DataRow getHeadRow() {
        return headRow;
    }

    public List<DataRow> getRows() {
        return rows;
    }

    public DataRow getFootRow() {
        return footRow;
    }

}
