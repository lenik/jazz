package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The source element allows authors to specify multiple alternative media resources for media elements. It does not represent anything on its own. 
  */
@SuppressWarnings("unchecked")
class _HtmlSourceTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlSourceTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The src attribute gives the address of the media resource. The value must be a valid non-empty URL potentially surrounded by spaces. This attribute must be present. 
      */
    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The type attribute gives the type of the media resource, to help the user agent determine if it can play this media resource before fetching it. If specified, its value must be a valid MIME type. The codecs parameter, which certain MIME types define, might be necessary to specify exactly how the resource is encoded. [RFC4281] 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The media attribute gives the intended media type of the media resource, to help the user agent determine if this media resource is useful to the user before fetching it. Its value must be a valid media query. 
      */
    public self_t media(Object val) {
        attr("media", val);
        return (self_t) this;
    }

}
