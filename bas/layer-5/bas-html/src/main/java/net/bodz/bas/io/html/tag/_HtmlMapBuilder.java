package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The map element, in conjunction with an img element and any area element descendants, defines an image map. The element represents its children. 
  */
@SuppressWarnings("unchecked")
class _HtmlMapBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlMapBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The name attribute gives the map a name so that it can be referenced. The attribute must be present and must have a non-empty value with no space characters. The value of the name attribute must not be a <a data-anolis-xref="compatibility caseless" href="infrastructure.html#compatibility-caseless">compatibility-caseless match for the value of the name attribute of another map element in the same document. If the id attribute is also specified, both attributes must have the same value. 
      */
    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

}
