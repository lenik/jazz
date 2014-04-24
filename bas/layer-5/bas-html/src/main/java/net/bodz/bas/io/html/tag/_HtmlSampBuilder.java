package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The samp element represents (sample) output from a program or computing system. 
  */
class _HtmlSampBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlSampBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
