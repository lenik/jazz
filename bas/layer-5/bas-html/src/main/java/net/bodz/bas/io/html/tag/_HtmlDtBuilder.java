package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The dt element itself, when used in a dl element, does not indicate that its contents are a term being defined, but this can be indicated using the dfn element. 
  */
class _HtmlDtBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDtBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
