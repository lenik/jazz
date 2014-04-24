package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The ruby, rb, rtc, and rt elements can be used for a variety of kinds of annotations, including in particular (though by no means limited to) those described below. For more details on Japanese Ruby in particular, and how to render Ruby for Japanese, see Requirements for Japanese Text Layout. [JLREQ] The rp element can be used as fallback content when ruby rendering is not supported. 
  */
class _HtmlRubyBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlRubyBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
