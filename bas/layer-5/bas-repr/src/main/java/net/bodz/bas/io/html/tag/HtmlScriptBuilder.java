package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The script element allows authors to include dynamic script and data blocks in their documents. The element does not represent content for the user. 
  */
public class HtmlScriptBuilder
        extends DecoratedHtmlTagBuilder<HtmlScriptBuilder> {

    public HtmlScriptBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The src attribute, if specified, gives the address of the external script resource to use. The value of the attribute must be a valid non-empty URL potentially surrounded by spaces identifying a script resource of the type given by the type attribute, if the attribute is present, or of the type "text/javascript", if the attribute is absent. A resource is a script resource of a given type if that type identifies a scripting language and the resource conforms with the requirements of that language's specification. 
      */
    public HtmlScriptBuilder src(String val) {
        attr("src", val);
        return this;
    }

    /**
      * The type attribute gives the language of the script or format of the data. If the attribute is present, its value must be a valid MIME type. The charset parameter must not be specified. The default, which is used if the attribute is absent, is "text/javascript". 
      */
    public HtmlScriptBuilder type(String val) {
        attr("type", val);
        return this;
    }

    /**
      * The charset attribute gives the character encoding of the external script resource. The attribute must not be specified if the src attribute is not present. If the attribute is set, its value must be an ASCII case-insensitive match for one of the <a data-anolis-xref="encoding label" href="infrastructure.html#encoding-label">labels of an encoding, and must specify the same encoding as the charset parameter of the Content-Type metadata of the external file, if any. [ENCODING] 
      */
    public HtmlScriptBuilder charset(String val) {
        attr("charset", val);
        return this;
    }

    public HtmlScriptBuilder async(String val) {
        attr("async", val);
        return this;
    }

    /**
      * The defer attribute may be specified even if the async attribute is specified, to cause legacy Web browsers that only support defer (and not async) to fall back to the defer behavior instead of the synchronous blocking behavior that is the default. 
      */
    public HtmlScriptBuilder defer(String val) {
        attr("defer", val);
        return this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. It controls, for scripts that are obtained from other origins, whether error information will be exposed. 
      */
    public HtmlScriptBuilder crossorigin(String val) {
        attr("crossorigin", val);
        return this;
    }

}
