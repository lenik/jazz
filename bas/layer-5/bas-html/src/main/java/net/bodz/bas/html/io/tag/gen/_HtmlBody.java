package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The body element exposes as event handler content attributes a number of the event handlers of
 * the Window object. It also mirrors their event handler IDL attributes.
 */
public class _HtmlBody<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlBody(HtmlDoc doc) {
        super(doc);
    }

    public self_t onafterprint(Object val) {
        return attr("onafterprint", val);
    }

    public self_t onbeforeprint(Object val) {
        return attr("onbeforeprint", val);
    }

    public self_t onbeforeunload(Object val) {
        return attr("onbeforeunload", val);
    }

    public self_t onhashchange(Object val) {
        return attr("onhashchange", val);
    }

    public self_t onmessage(Object val) {
        return attr("onmessage", val);
    }

    public self_t onoffline(Object val) {
        return attr("onoffline", val);
    }

    public self_t ononline(Object val) {
        return attr("ononline", val);
    }

    public self_t onpagehide(Object val) {
        return attr("onpagehide", val);
    }

    public self_t onpageshow(Object val) {
        return attr("onpageshow", val);
    }

    public self_t onpopstate(Object val) {
        return attr("onpopstate", val);
    }

    public self_t onstorage(Object val) {
        return attr("onstorage", val);
    }

    public self_t onunload(Object val) {
        return attr("onunload", val);
    }

}
