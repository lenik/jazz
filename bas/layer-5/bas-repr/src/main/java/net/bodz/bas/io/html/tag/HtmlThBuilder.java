package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The tbody elements in this example identify the range of the row groups. 
  */
public class HtmlThBuilder
        extends DecoratedHtmlTagBuilder<HtmlThBuilder> {

    public HtmlThBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlThBuilder colspan(String val) {
        attr("colspan", val);
        return this;
    }

    public HtmlThBuilder rowspan(String val) {
        attr("rowspan", val);
        return this;
    }

    public HtmlThBuilder headers(String val) {
        attr("headers", val);
        return this;
    }

    /**
      * The scope attribute's missing value default is the auto state. 
      */
    public HtmlThBuilder scope(String val) {
        attr("scope", val);
        return this;
    }

    public HtmlThBuilder abbr(String val) {
        attr("abbr", val);
        return this;
    }

}
