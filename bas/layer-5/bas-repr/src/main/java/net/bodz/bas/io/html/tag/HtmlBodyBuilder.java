package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The body element exposes as event handler content attributes a number of the event handlers of the Window object. It also mirrors their event handler IDL attributes. 
  */
public class HtmlBodyBuilder
        extends DecoratedHtmlTagBuilder<HtmlBodyBuilder> {

    public HtmlBodyBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlBodyBuilder onafterprint(String val) {
        attr("onafterprint", val);
        return this;
    }

    public HtmlBodyBuilder onbeforeprint(String val) {
        attr("onbeforeprint", val);
        return this;
    }

    public HtmlBodyBuilder onbeforeunload(String val) {
        attr("onbeforeunload", val);
        return this;
    }

    public HtmlBodyBuilder onhashchange(String val) {
        attr("onhashchange", val);
        return this;
    }

    public HtmlBodyBuilder onmessage(String val) {
        attr("onmessage", val);
        return this;
    }

    public HtmlBodyBuilder onoffline(String val) {
        attr("onoffline", val);
        return this;
    }

    public HtmlBodyBuilder ononline(String val) {
        attr("ononline", val);
        return this;
    }

    public HtmlBodyBuilder onpagehide(String val) {
        attr("onpagehide", val);
        return this;
    }

    public HtmlBodyBuilder onpageshow(String val) {
        attr("onpageshow", val);
        return this;
    }

    public HtmlBodyBuilder onpopstate(String val) {
        attr("onpopstate", val);
        return this;
    }

    public HtmlBodyBuilder onstorage(String val) {
        attr("onstorage", val);
        return this;
    }

    public HtmlBodyBuilder onunload(String val) {
        attr("onunload", val);
        return this;
    }

}
