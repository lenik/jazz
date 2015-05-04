package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The noscript element is a blunt instrument. Sometimes, scripts might be enabled, but for some reason the page's script might fail. For this reason, it's generally better to avoid using noscript, and to instead design the script to change the page from being a scriptless page to a scripted page on the fly, as in the next example: 
  */
public class _HtmlNoscriptTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlNoscriptTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
