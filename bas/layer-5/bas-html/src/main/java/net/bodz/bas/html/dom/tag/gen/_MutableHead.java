package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The title element is a required child in most situations, but when a higher-level protocol provides title information, e.g. in the Subject line of an e-mail when HTML is used as an e-mail authoring format, the title element can be omitted. 
  */
public class _MutableHead<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableHead(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
