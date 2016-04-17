package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The p element should not be used when a more specific element is more appropriate. 
  */
public class _MutableP<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableP(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
