package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The script element allows authors to include dynamic script and data blocks in
 * their documents. The element does not represent content for the user.
 */
public class _HtmlScript<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlScript(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The src attribute, if specified, gives the address of the external script resource to use.
     * The value of the attribute must be a valid non-empty URL potentially surrounded by spaces
     * identifying a script resource of the type given by the type attribute, if the attribute is
     * present, or of the type "text/javascript", if the attribute is absent. A resource is a script
     * resource of a given type if that type identifies a scripting language and the resource
     * conforms with the requirements of that language's specification.
     */
    public self_t src(Object val) {
        return attr("src", val);
    }

    /**
     * The type attribute gives the language of the script or format of the data. If the attribute
     * is present, its value must be a valid MIME type. The charset parameter must not be specified.
     * The default, which is used if the attribute is absent, is "text/javascript".
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

    /**
     * The charset attribute gives the character encoding of the external script resource. The
     * attribute must not be specified if the src attribute is not present. If the attribute is set,
     * its value must be an ASCII case-insensitive match for one of the <a
     * data-anolis-xref="encoding label" href="infrastructure.html#encoding-label">labels of an
     * encoding, and must specify the same encoding as the charset parameter of the Content-Type
     * metadata of the external file, if any. [ENCODING]
     */
    public self_t charset(Object val) {
        return attr("charset", val);
    }

    public self_t async(Object val) {
        return attr("async", val);
    }

    /**
     * The defer attribute may be specified even if the async attribute is specified, to cause
     * legacy Web browsers that only support defer (and not async) to fall back to the defer
     * behavior instead of the synchronous blocking behavior that is the default.
     */
    public self_t defer(Object val) {
        return attr("defer", val);
    }

    /**
     * The crossorigin attribute is a CORS settings attribute. It controls, for scripts that are
     * obtained from other origins, whether error information will be exposed.
     */
    public self_t crossorigin(Object val) {
        return attr("crossorigin", val);
    }

}
