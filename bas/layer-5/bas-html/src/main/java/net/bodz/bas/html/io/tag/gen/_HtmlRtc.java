package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * An rtc element's end tag may be omitted if the rtc element is immediately followed by an rb, rtc
 * or rp element, or if there is no more content in the parent element. Allowed ARIA role attribute
 * values: Any role value. Allowed ARIA state and property attributes: Global aria-* attributes Any
 * aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM APIs--> Uses
 * HTMLElement. <!--TOPIC:HTML--> The rtc element marks a ruby text container for ruby text
 * components in a ruby annotation. When it is the child of a ruby element it doesn't represent
 * anything itself, but its parent ruby element uses it as part of determining what it represents.
 */
public class _HtmlRtc<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlRtc(HtmlDoc doc) {
        super(doc);
    }

}
