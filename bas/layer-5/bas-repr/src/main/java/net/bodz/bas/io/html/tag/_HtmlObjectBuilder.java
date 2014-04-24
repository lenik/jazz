package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The object element supports dimension attributes. 
  */
@SuppressWarnings("unchecked")
class _HtmlObjectBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlObjectBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * If the URL of the given resource is not about:blank, the element's nested browsing context must then be navigated<!--DONAV object--> to that resource, with replacement enabled, and with the object element's document's browsing context as the source browsing context. (The data attribute of the object element doesn't get updated if the browsing context gets further navigated to other locations.) 
      */
    public self_t data(String val) {
        attr("data", val);
        return (self_t) this;
    }

    /**
      * The type attribute, if present, specifies the type of the resource. If present, the attribute must be a valid MIME type. 
      */
    public self_t type(String val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The typemustmatch attribute must not be specified unless both the data attribute and the type attribute are present. 
      */
    public self_t typemustmatch(String val) {
        attr("typemustmatch", val);
        return (self_t) this;
    }

    /**
      * The name attribute, if present, must be a valid browsing context name. The given value is used to name the nested browsing context, if applicable. 
      */
    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The usemap attribute, if present while the object element represents an image, can indicate that the object has an associated image map. The attribute must be ignored if the object element doesn't represent an image. 
      */
    public self_t usemap(String val) {
        attr("usemap", val);
        return (self_t) this;
    }

    /**
      * The form attribute is used to explicitly associate the object element with its form owner. 
      */
    public self_t form(String val) {
        attr("form", val);
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
