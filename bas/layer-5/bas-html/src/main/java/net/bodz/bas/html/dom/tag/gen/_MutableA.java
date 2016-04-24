package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The a element may be wrapped around entire paragraphs, lists, tables, and so forth, even entire sections, so long as there is no interactive content within (e.g. buttons or other links). This example shows how this can be used to make an entire advertising block into a link: 
  */
@SuppressWarnings("unchecked")
public class _MutableA<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableA(IHtmlTag parent) {
        super(parent, "a");
    }

    public self_t href(Object val) {
        attr("href", val);
        return (self_t) this;
    }

    public self_t target(Object val) {
        attr("target", val);
        return (self_t) this;
    }

    public self_t download(Object val) {
        attr("download", val);
        return (self_t) this;
    }

    public self_t rel(Object val) {
        attr("rel", val);
        return (self_t) this;
    }

    public self_t hreflang(Object val) {
        attr("hreflang", val);
        return (self_t) this;
    }

    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

}
