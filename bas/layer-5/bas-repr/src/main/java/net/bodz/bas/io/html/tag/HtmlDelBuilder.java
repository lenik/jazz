package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The del element represents a removal from the document. 
  */
public class HtmlDelBuilder
        extends DecoratedHtmlTagBuilder<HtmlDelBuilder> {

    public HtmlDelBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlDelBuilder cite(String val) {
        attr("cite", val);
        return this;
    }

    public HtmlDelBuilder datetime(String val) {
        attr("datetime", val);
        return this;
    }

}
