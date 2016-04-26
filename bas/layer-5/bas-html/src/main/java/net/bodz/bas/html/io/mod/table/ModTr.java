package net.bodz.bas.html.io.mod.table;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlTr;

public class ModTr
        extends HtmlTr {

    DataRow row;

    public ModTr(HtmlDoc doc, DataRow row) {
        super(doc);
        this.row = row;
    }

    @Override
    public ModTh th() {
        StringBuilder sb = new StringBuilder();
        row.add(sb);
        return begin("th", new ModTh(doc, sb));
    }

    @Override
    public ModTd td() {
        StringBuilder sb = new StringBuilder();
        row.add(sb);
        return begin("td", new ModTd(doc, sb));
    }

}
