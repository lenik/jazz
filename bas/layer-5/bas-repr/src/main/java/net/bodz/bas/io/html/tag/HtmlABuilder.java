package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The a element may be wrapped around entire paragraphs, lists, tables, and so forth, even entire sections, so long as there is no interactive content within (e.g. buttons or other links). This example shows how this can be used to make an entire advertising block into a link: 
  */
public class HtmlABuilder
        extends DecoratedHtmlTagBuilder<HtmlABuilder> {

    public HtmlABuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlABuilder href(String val) {
        attr("href", val);
        return this;
    }

    public HtmlABuilder target(String val) {
        attr("target", val);
        return this;
    }

    public HtmlABuilder download(String val) {
        attr("download", val);
        return this;
    }

    public HtmlABuilder rel(String val) {
        attr("rel", val);
        return this;
    }

    public HtmlABuilder hreflang(String val) {
        attr("hreflang", val);
        return this;
    }

    public HtmlABuilder type(String val) {
        attr("type", val);
        return this;
    }

}
