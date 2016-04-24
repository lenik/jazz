package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The base element allows authors to specify the document base URL for the purposes of resolving relative URLs, and the name of the default browsing context for the purposes of following hyperlinks. The element does not represent any content beyond this information. 
  */
@SuppressWarnings("unchecked")
public class _MutableBase<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableBase(IHtmlTag parent) {
        super(parent, "base");
    }

    /**
      * The href content attribute, if specified, must contain a valid URL potentially surrounded by spaces. 
      */
    public self_t href(Object val) {
        attr("href", val);
        return (self_t) this;
    }

    /**
      * The target attribute, if specified, must contain a valid browsing context name or keyword, which specifies which browsing context is to be used as the default when hyperlinks and forms in the Document cause navigation. 
      */
    public self_t target(Object val) {
        attr("target", val);
        return (self_t) this;
    }

}
