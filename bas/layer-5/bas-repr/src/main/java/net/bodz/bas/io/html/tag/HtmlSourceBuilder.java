package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The source element allows authors to specify multiple alternative media resources for media elements. It does not represent anything on its own. 
  */
public class HtmlSourceBuilder
        extends DecoratedHtmlTagBuilder<HtmlSourceBuilder> {

    public HtmlSourceBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The src attribute gives the address of the media resource. The value must be a valid non-empty URL potentially surrounded by spaces. This attribute must be present. 
      */
    public HtmlSourceBuilder src(String val) {
        attr("src", val);
        return this;
    }

    /**
      * The type attribute gives the type of the media resource, to help the user agent determine if it can play this media resource before fetching it. If specified, its value must be a valid MIME type. The codecs parameter, which certain MIME types define, might be necessary to specify exactly how the resource is encoded. [RFC4281] 
      */
    public HtmlSourceBuilder type(String val) {
        attr("type", val);
        return this;
    }

    /**
      * The media attribute gives the intended media type of the media resource, to help the user agent determine if this media resource is useful to the user before fetching it. Its value must be a valid media query. 
      */
    public HtmlSourceBuilder media(String val) {
        attr("media", val);
        return this;
    }

}
