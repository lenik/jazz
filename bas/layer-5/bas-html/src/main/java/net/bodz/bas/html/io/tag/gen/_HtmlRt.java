package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * An rt element's end tag may be omitted if the rt element is immediately followed by an rb, rt,
 * rtc or rp element, or if there is no more content in the parent element. Allowed ARIA role
 * attribute values: Any role value. Allowed ARIA state and property attributes: Global aria-*
 * attributes Any aria-* attributes applicable to the allowed roles. DOM interface:<!--TOPIC:DOM
 * APIs--> Uses HTMLElement. <!--TOPIC:HTML--> The rt element marks the ruby text component of a
 * ruby annotation. When it is the child of a ruby element or of an rtc element that is itself the
 * child of a ruby element, it doesn't represent anything itself, but its ancestor ruby element uses
 * it as part of determining what it represents.
 */
public class _HtmlRt<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlRt(HtmlDoc doc) {
        super(doc);
    }

}
