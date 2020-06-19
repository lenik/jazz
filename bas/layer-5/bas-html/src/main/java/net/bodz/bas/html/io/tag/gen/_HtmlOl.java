package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * <!--TOPIC:HTML-->The ol element represents a list of items, where the items have been
 * intentionally ordered, such that changing the order would change the meaning of the document.
 */
public class _HtmlOl<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlOl(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The reversed attribute is a boolean attribute. If present, it indicates that the list is a
     * descending list (..., 3, 2, 1). If the attribute is omitted, the list is an ascending list
     * (1, 2, 3, ...).
     */
    public self_t reversed(Object val) {
        return attr("reversed", val);
    }

    /**
     * The start attribute, if present, must be a valid integer giving the ordinal value of the
     * first list item.
     */
    public self_t start(Object val) {
        return attr("start", val);
    }

    /**
     * The type attribute can be used to specify the kind of marker to use in the list, in the cases
     * where that matters (e.g. because items are to be referenced by their number/letter). The
     * attribute, if specified, must have a value that is a case-sensitive match for one of the
     * characters given in the first cell of one of the rows of the following table. The type
     * attribute represents the state given in the cell in the second column of the row whose first
     * cell matches the attribute's value; if none of the cells match, or if the attribute is
     * omitted, then the attribute represents the decimal state.
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

}
