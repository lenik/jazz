package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The section element is not a generic container element. When an element is needed only for styling purposes or as a convenience for scripting, authors are encouraged to use the div element instead. A general rule is that the section element is appropriate only if the element's contents would be listed explicitly in the document's outline. 
  */
class _HtmlSectionBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlSectionBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
