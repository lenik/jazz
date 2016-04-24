package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The main element is not suitable for use to identify the main content areas of sub sections of a document or application. The simplest solution is to not mark up the main content of a sub section at all, and just leave it as implicit, but an author could use a grouping content or sectioning content element as appropriate. 
  */
public class _MutableMain<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableMain(IHtmlTag parent) {
        super(parent, "main");
    }

}
