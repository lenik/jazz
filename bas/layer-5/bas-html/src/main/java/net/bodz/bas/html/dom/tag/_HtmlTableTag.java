package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The table element takes part in the table model. Tables have rows, columns, and cells given by their descendants. The rows and columns form a grid; a table's cells must completely cover that grid without overlap. 
  */
@SuppressWarnings("unchecked")
public class _HtmlTableTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTableTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The border attribute may be specified on a table element to explicitly indicate that the table element is not being used for layout purposes. If specified, the attribute's value must either be the empty string or the value "1". The attribute is used by certain user agents as an indication that borders should be drawn around cells of the table. 
      */
    public self_t border(Object val) {
        attr("border", val);
        return (self_t) this;
    }

}
