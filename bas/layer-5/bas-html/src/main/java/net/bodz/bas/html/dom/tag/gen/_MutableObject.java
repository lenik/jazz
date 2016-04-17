package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The object element supports dimension attributes. 
  */
@SuppressWarnings("unchecked")
public class _MutableObject<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableObject(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * If the URL of the given resource is not about:blank, the element's nested browsing context must then be navigated<!--DONAV object--> to that resource, with replacement enabled, and with the object element's document's browsing context as the source browsing context. (The data attribute of the object element doesn't get updated if the browsing context gets further navigated to other locations.) 
      */
    public self_t data(Object val) {
        attr("data", val);
        return (self_t) this;
    }

    /**
      * The type attribute, if present, specifies the type of the resource. If present, the attribute must be a valid MIME type. 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The typemustmatch attribute must not be specified unless both the data attribute and the type attribute are present. 
      */
    public self_t typemustmatch(Object val) {
        attr("typemustmatch", val);
        return (self_t) this;
    }

    /**
      * The name attribute, if present, must be a valid browsing context name. The given value is used to name the nested browsing context, if applicable. 
      */
    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The usemap attribute, if present while the object element represents an image, can indicate that the object has an associated image map. The attribute must be ignored if the object element doesn't represent an image. 
      */
    public self_t usemap(Object val) {
        attr("usemap", val);
        return (self_t) this;
    }

    /**
      * The form attribute is used to explicitly associate the object element with its form owner. 
      */
    public self_t form(Object val) {
        attr("form", val);
        return (self_t) this;
    }

    public self_t width(Object val) {
        attr("width", val);
        return (self_t) this;
    }

    public self_t height(Object val) {
        attr("height", val);
        return (self_t) this;
    }

}
