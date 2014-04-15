package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The label element's exact default presentation and behavior, in particular what its activation behavior might be, if anything, should match the platform's label behavior. The activation behavior of a label element for events targeted at interactive content descendants of a label element, and any descendants of those interactive content descendants, must be to do nothing. 
  */
public class HtmlLabelBuilder
        extends DecoratedHtmlTagBuilder<HtmlLabelBuilder> {

    public HtmlLabelBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The form attribute is used to explicitly associate the label element with its form owner. 
      */
    public HtmlLabelBuilder form(String val) {
        attr("form", val);
        return this;
    }

    /**
      * The for attribute may be specified to indicate a form control with which the caption is to be associated. If the attribute is specified, the attribute's value must be the ID of a labelable element in the same Document as the label element. If the attribute is specified and there is an element in the Document whose ID is equal to the value of the for attribute, and the first such element is a labelable element, then that element is the label element's labeled control. 
      */
    public HtmlLabelBuilder for_(String val) {
        attr("for", val);
        return this;
    }

}
