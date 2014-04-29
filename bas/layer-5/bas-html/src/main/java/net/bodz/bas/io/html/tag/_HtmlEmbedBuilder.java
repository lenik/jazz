package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The embed element supports dimension attributes. 
  */
@SuppressWarnings("unchecked")
class _HtmlEmbedBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlEmbedBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * Navigate<!--DONAV embed--> the nested browsing context to the fetched resource, with replacement enabled, and with the embed element's document's browsing context as the source browsing context. (The src attribute of the embed element doesn't get updated if the browsing context gets further navigated to other locations.) The embed element now represents its associated nested browsing context. Otherwise, find and instantiate an appropriate plugin based on the content's type, and hand that plugin the content of the resource, replacing any previously instantiated plugin for the element. The embed element now represents this plugin instance. 
      */
    public self_t src(String val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The type attribute, if present, gives the MIME type by which the plugin to instantiate is selected. The value must be a valid MIME type. If both the type attribute and the src attribute are present, then the type attribute must specify the same type as the explicit Content-Type metadata of the resource given by the src attribute. 
      */
    public self_t type(String val) {
        attr("type", val);
        return (self_t) this;
    }

    public self_t width(String val) {
        attr("width", val);
        return (self_t) this;
    }

    public self_t height(String val) {
        attr("height", val);
        return (self_t) this;
    }

}