package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * A dd element's end tag may be omitted if the dd element is immediately followed by another dd element or a dt element, or if there is no more content in the parent element. Allowed ARIA role attribute values: Any role value. Allowed ARIA state and property attributes: Global aria-* attributes Any aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM APIs--> Uses HTMLElement. <!--TOPIC:HTML-->The dd element represents the description, definition, or value, part of a term-description group in a description list (dl element). 
  */
public class HtmlDdBuilder
        extends DecoratedHtmlTagBuilder<HtmlDdBuilder> {

    public HtmlDdBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
