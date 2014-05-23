package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The bdo element represents explicit text directionality formatting control for its children. It allows authors to override the Unicode bidirectional algorithm by explicitly specifying a direction override. [BIDI] 
  */
class _HtmlBdoTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlBdoTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
