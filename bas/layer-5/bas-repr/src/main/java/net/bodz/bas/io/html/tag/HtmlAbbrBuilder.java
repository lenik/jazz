package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The abbr element represents an abbreviation or acronym, optionally with its expansion. The title attribute may be used to provide an expansion of the abbreviation. The attribute, if specified, must contain an expansion of the abbreviation, and nothing else. 
  */
public class HtmlAbbrBuilder
        extends DecoratedHtmlTagBuilder<HtmlAbbrBuilder> {

    public HtmlAbbrBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
