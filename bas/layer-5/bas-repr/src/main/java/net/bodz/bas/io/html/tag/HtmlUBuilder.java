package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The u element represents a span of text with an unarticulated, though explicitly rendered, non-textual annotation, such as labeling the text as being a proper name in Chinese text (a Chinese proper name mark), or labeling the text as being misspelt. 
  */
public class HtmlUBuilder
        extends DecoratedHtmlTagBuilder<HtmlUBuilder> {

    public HtmlUBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
