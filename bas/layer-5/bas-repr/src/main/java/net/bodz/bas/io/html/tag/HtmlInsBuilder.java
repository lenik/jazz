package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The ins element represents an addition to the document. 
  */
public class HtmlInsBuilder
        extends DecoratedHtmlTagBuilder<HtmlInsBuilder> {

    public HtmlInsBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlInsBuilder cite(String val) {
        attr("cite", val);
        return this;
    }

    public HtmlInsBuilder datetime(String val) {
        attr("datetime", val);
        return this;
    }

}
