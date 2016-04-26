package net.bodz.bas.html.io.mod.table;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlTfoot;
import net.bodz.bas.meta.codegen.CopyAndPaste;

@CopyAndPaste(ModTr.class)
public class ModTfoot
        extends HtmlTfoot {

    DataRow row;

    public ModTfoot(HtmlDoc doc, DataRow row) {
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
