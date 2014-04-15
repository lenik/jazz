package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The cite element represents a reference to a creative work. It must include the title of the work or the name of the author(person, people or organization) or an URL reference, which may be in an abbreviated form as per the conventions used for the addition of citation metadata. 
  */
public class HtmlCiteBuilder
        extends DecoratedHtmlTagBuilder<HtmlCiteBuilder> {

    public HtmlCiteBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
