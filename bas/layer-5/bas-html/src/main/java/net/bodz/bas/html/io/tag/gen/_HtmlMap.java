package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * <!--TOPIC:HTML-->The map element, in conjunction with an img element and any area element
 * descendants, defines an image map. The element represents its children.
 */
public class _HtmlMap<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlMap(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The name attribute gives the map a name so that it can be referenced. The attribute must be
     * present and must have a non-empty value with no space characters. The value of the name
     * attribute must not be a <a data-anolis-xref="compatibility caseless"
     * href="infrastructure.html#compatibility-caseless">compatibility-caseless match for the value
     * of the name attribute of another map element in the same document. If the id attribute is
     * also specified, both attributes must have the same value.
     */
    public self_t name(Object val) {
        return attr("name", val);
    }

}
