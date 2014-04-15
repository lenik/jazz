package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The q element must not be used in place of quotation marks that do not represent quotes; for example, it is inappropriate to use the q element for marking up sarcastic statements. 
  */
public class HtmlQBuilder
        extends DecoratedHtmlTagBuilder<HtmlQBuilder> {

    public HtmlQBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlQBuilder cite(String val) {
        attr("cite", val);
        return this;
    }

}
