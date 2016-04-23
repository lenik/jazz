package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The table element takes part in the table model. Tables have rows, columns, and cells given by
 * their descendants. The rows and columns form a grid; a table's cells must completely cover that
 * grid without overlap.
 */
public class _HtmlTable<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlTable(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The border attribute may be specified on a table element to explicitly indicate that the
     * table element is not being used for layout purposes. If specified, the attribute's value must
     * either be the empty string or the value "1". The attribute is used by certain user agents as
     * an indication that borders should be drawn around cells of the table.
     */
    public self_t border(Object val) {
        return attr("border", val);
    }

}
