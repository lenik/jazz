package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The source element allows authors to specify multiple alternative media resources for media elements. It does not represent anything on its own. 
  */
@SuppressWarnings("unchecked")
public class _MutableSource<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableSource(IHtmlTag parent) {
        super(parent, "source");
    }

    /**
      * The src attribute gives the address of the media resource. The value must be a valid non-empty URL potentially surrounded by spaces. This attribute must be present. 
      */
    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The type attribute gives the type of the media resource, to help the user agent determine if it can play this media resource before fetching it. If specified, its value must be a valid MIME type. The codecs parameter, which certain MIME types define, might be necessary to specify exactly how the resource is encoded. [RFC6381] 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

}
