package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The var element represents a variable. This could be an actual variable in a mathematical expression or programming context, an identifier representing a constant, a symbol identifying a physical quantity, a function parameter, or just be a term used as a placeholder in prose. 
  */
public class HtmlVarBuilder
        extends DecoratedHtmlTagBuilder<HtmlVarBuilder> {

    public HtmlVarBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
