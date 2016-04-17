package net.bodz.bas.html.io.mod.table;

import java.util.List;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlTbody;

public class ModTbody
        extends HtmlTbody {

    List<DataRow> rows;

    public ModTbody(HtmlDoc doc, List<DataRow> rows) {
        super(doc);
        if (rows == null)
            throw new NullPointerException("rows");
        this.rows = rows;
    }

    @Override
    public ModTr tr() {
        DataRow row = new DataRow();
        rows.add(row);
        return new ModTr(doc, row);
    }

}
