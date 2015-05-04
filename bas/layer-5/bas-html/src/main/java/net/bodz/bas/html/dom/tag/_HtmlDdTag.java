package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * A dd element's end tag may be omitted if the dd element is immediately followed by another dd element or a dt element, or if there is no more content in the parent element. Allowed ARIA role attribute values: Any role value. Allowed ARIA state and property attributes: Global aria-* attributes Any aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM APIs--> Uses HTMLElement. <!--TOPIC:HTML-->The dd element represents the description, definition, or value, part of a term-description group in a description list (dl element). 
  */
public class _HtmlDdTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlDdTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
