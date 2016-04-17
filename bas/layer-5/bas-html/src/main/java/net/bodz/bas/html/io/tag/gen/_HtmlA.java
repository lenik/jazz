package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The a element may be wrapped around entire paragraphs, lists, tables, and so forth, even entire
 * sections, so long as there is no interactive content within (e.g. buttons or other links). This
 * example shows how this can be used to make an entire advertising block into a link:
 */
public class _HtmlA<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlA(HtmlDoc doc) {
        super(doc);
    }

    public self_t href(Object val) {
        return attr("href", val);
    }

    public self_t target(Object val) {
        return attr("target", val);
    }

    public self_t download(Object val) {
        return attr("download", val);
    }

    public self_t rel(Object val) {
        return attr("rel", val);
    }

    public self_t hreflang(Object val) {
        return attr("hreflang", val);
    }

    public self_t type(Object val) {
        return attr("type", val);
    }

}
