package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The legend element represents a caption for the rest of the contents of the legend element's parent fieldset element, if any. 
  */
class _HtmlLegendBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlLegendBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
