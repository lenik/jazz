package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The body element exposes as event handler content attributes a number of the event handlers of the Window object. It also mirrors their event handler IDL attributes. 
  */
@SuppressWarnings("unchecked")
class _HtmlBodyBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlBodyBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t onafterprint(String val) {
        attr("onafterprint", val);
        return (self_t) this;
    }

    public self_t onbeforeprint(String val) {
        attr("onbeforeprint", val);
        return (self_t) this;
    }

    public self_t onbeforeunload(String val) {
        attr("onbeforeunload", val);
        return (self_t) this;
    }

    public self_t onhashchange(String val) {
        attr("onhashchange", val);
        return (self_t) this;
    }

    public self_t onmessage(String val) {
        attr("onmessage", val);
        return (self_t) this;
    }

    public self_t onoffline(String val) {
        attr("onoffline", val);
        return (self_t) this;
    }

    public self_t ononline(String val) {
        attr("ononline", val);
        return (self_t) this;
    }

    public self_t onpagehide(String val) {
        attr("onpagehide", val);
        return (self_t) this;
    }

    public self_t onpageshow(String val) {
        attr("onpageshow", val);
        return (self_t) this;
    }

    public self_t onpopstate(String val) {
        attr("onpopstate", val);
        return (self_t) this;
    }

    public self_t onstorage(String val) {
        attr("onstorage", val);
        return (self_t) this;
    }

    public self_t onunload(String val) {
        attr("onunload", val);
        return (self_t) this;
    }

}
