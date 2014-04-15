package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The b element should be used as a last resort when no other element is more appropriate. In particular, headings should use the h1 to h6 elements, stress emphasis should use the em element, importance should be denoted with the strong element, and text marked or highlighted should use the mark element. 
  */
public class HtmlBBuilder
        extends DecoratedHtmlTagBuilder<HtmlBBuilder> {

    public HtmlBBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
