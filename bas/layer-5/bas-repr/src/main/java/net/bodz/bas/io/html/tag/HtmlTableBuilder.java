package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The table element takes part in the table model. Tables have rows, columns, and cells given by their descendants. The rows and columns form a grid; a table's cells must completely cover that grid without overlap. 
  */
public class HtmlTableBuilder
        extends DecoratedHtmlTagBuilder<HtmlTableBuilder> {

    public HtmlTableBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
