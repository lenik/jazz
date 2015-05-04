package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The ruby, rb, rtc, and rt elements can be used for a variety of kinds of annotations, including in particular (though by no means limited to) those described below. For more details on Japanese Ruby in particular, and how to render Ruby for Japanese, see Requirements for Japanese Text Layout. [JLREQ] The rp element can be used as fallback content when ruby rendering is not supported. 
  */
public class _HtmlRubyTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlRubyTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
