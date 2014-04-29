package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML--><!--CLEANUP-->The pre element represents a block of preformatted text, in which structure is represented by typographic conventions rather than by elements. 
  */
class _HtmlPreBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlPreBuilder(IXmlTagBuilder o) {
        super(o);
    }

}