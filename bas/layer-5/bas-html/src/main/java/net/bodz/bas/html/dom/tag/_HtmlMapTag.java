package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The map element, in conjunction with an img element and any area element descendants, defines an image map. The element represents its children. 
  */
@SuppressWarnings("unchecked")
class _HtmlMapTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlMapTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The name attribute gives the map a name so that it can be referenced. The attribute must be present and must have a non-empty value with no space characters. The value of the name attribute must not be a <a data-anolis-xref="compatibility caseless" href="infrastructure.html#compatibility-caseless">compatibility-caseless match for the value of the name attribute of another map element in the same document. If the id attribute is also specified, both attributes must have the same value. 
      */
    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

}
