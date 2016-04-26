package net.bodz.bas.html.io.mod.table;

import java.util.List;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlDiv;
import net.bodz.bas.html.io.tag.HtmlTable;

public class ModDiv
        extends HtmlDiv {

    List<ModTable> tables;

    public ModDiv(HtmlDoc doc, List<ModTable> tables) {
        super(doc);
        this.tables = tables;
    }

    @Override
    public ModDiv div() {
        return begin("div", new ModDiv(doc, tables));
    };

    @Override
    public HtmlTable table() {
        ModTable table = begin("table", new ModTable(doc));
        tables.add(table);
        return table;
    }

}
