package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The body element exposes as event handler content attributes a number of the event handlers of the Window object. It also mirrors their event handler IDL attributes. 
  */
@SuppressWarnings("unchecked")
public class _MutableBody<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableBody(IHtmlTag parent) {
        super(parent, "body");
    }

    public self_t onafterprint(Object val) {
        attr("onafterprint", val);
        return (self_t) this;
    }

    public self_t onbeforeprint(Object val) {
        attr("onbeforeprint", val);
        return (self_t) this;
    }

    public self_t onbeforeunload(Object val) {
        attr("onbeforeunload", val);
        return (self_t) this;
    }

    public self_t onhashchange(Object val) {
        attr("onhashchange", val);
        return (self_t) this;
    }

    public self_t onmessage(Object val) {
        attr("onmessage", val);
        return (self_t) this;
    }

    public self_t onoffline(Object val) {
        attr("onoffline", val);
        return (self_t) this;
    }

    public self_t ononline(Object val) {
        attr("ononline", val);
        return (self_t) this;
    }

    public self_t onpagehide(Object val) {
        attr("onpagehide", val);
        return (self_t) this;
    }

    public self_t onpageshow(Object val) {
        attr("onpageshow", val);
        return (self_t) this;
    }

    public self_t onpopstate(Object val) {
        attr("onpopstate", val);
        return (self_t) this;
    }

    public self_t onstorage(Object val) {
        attr("onstorage", val);
        return (self_t) this;
    }

    public self_t onunload(Object val) {
        attr("onunload", val);
        return (self_t) this;
    }

}
