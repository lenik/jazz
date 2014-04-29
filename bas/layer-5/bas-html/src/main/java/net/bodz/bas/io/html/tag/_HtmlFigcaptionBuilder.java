package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The figcaption element represents a caption or legend for the rest of the contents of the figcaption element's parent figure element, if any. 
  */
class _HtmlFigcaptionBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlFigcaptionBuilder(IXmlTagBuilder o) {
        super(o);
    }

}