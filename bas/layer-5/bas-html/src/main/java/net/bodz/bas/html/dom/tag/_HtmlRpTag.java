package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * An rp element's end tag may be omitted if the rp element is immediately followed by an rb, rt, rtc or rp element, or if there is no more content in the parent element. Allowed ARIA role attribute values: Any role value. Allowed ARIA state and property attributes: Global aria-* attributes Any aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM APIs--> Uses HTMLElement. <!--TOPIC:HTML--> The rp element is used to provide fallback text to be shown by user agents that don't support ruby annotations. One widespread convention is to provide parentheses around the ruby text component of a ruby annotation. 
  */
class _HtmlRpTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlRpTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
