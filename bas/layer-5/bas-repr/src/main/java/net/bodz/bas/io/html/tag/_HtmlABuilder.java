package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The a element may be wrapped around entire paragraphs, lists, tables, and so forth, even entire sections, so long as there is no interactive content within (e.g. buttons or other links). This example shows how this can be used to make an entire advertising block into a link: 
  */
@SuppressWarnings("unchecked")
class _HtmlABuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlABuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t href(String val) {
        attr("href", val);
        return (self_t) this;
    }

    public self_t target(String val) {
        attr("target", val);
        return (self_t) this;
    }

    public self_t download(String val) {
        attr("download", val);
        return (self_t) this;
    }

    public self_t rel(String val) {
        attr("rel", val);
        return (self_t) this;
    }

    public self_t hreflang(String val) {
        attr("hreflang", val);
        return (self_t) this;
    }

    public self_t type(String val) {
        attr("type", val);
        return (self_t) this;
    }

}
