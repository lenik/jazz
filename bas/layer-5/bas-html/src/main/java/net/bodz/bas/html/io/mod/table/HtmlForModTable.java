package net.bodz.bas.html.io.mod.table;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.tag.HtmlTable;

public class HtmlForModTable
        extends AbstractRecHtmlOut<HtmlForModTable> {

    List<ModTable> tables = new ArrayList<>();

    public HtmlForModTable(HtmlDoc doc) {
        super(doc);
    }

    @Override
    public HtmlTable table() {
        ModTable table = new ModTable(doc);
        tables.add(table);
        return table;
    }

    public List<ModTable> getTables() {
        return tables;
    }

}
