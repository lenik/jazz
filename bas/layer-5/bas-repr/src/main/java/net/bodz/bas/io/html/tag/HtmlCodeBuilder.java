package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The code element represents a fragment of computer code. This could be an XML element name, a file name, a computer program, or any other string that a computer would recognize. 
  */
public class HtmlCodeBuilder
        extends DecoratedHtmlTagBuilder<HtmlCodeBuilder> {

    public HtmlCodeBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
