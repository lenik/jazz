package net.bodz.bas.html.io.mod.table;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

public class HtmlForModTable
        extends AbstractRecHtmlOut<HtmlForModTable> {

    List<ModTable> tables = new ArrayList<ModTable>();

    public HtmlForModTable(HtmlDoc doc) {
        super(doc);
    }

    @Override
    public ModDiv div() {
        return begin("div", new ModDiv(doc, tables));
    };

    @Override
    public ModTable table() {
        ModTable table = begin("table", new ModTable(doc));
        tables.add(table);
        return table;
    }

    public List<ModTable> getTables() {
        return tables;
    }

}
