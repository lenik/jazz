package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The div elements used in the code samples above and below are not intended to convey any semantic meaning and are used only to create a non-inline rendering of the grouped fieldset controls. 
  */
public class HtmlFieldsetBuilder
        extends DecoratedHtmlTagBuilder<HtmlFieldsetBuilder> {

    public HtmlFieldsetBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The disabled attribute, when specified, causes all the form control descendants of the fieldset element, excluding those that are descendants of the fieldset element's first legend element child, if any, to be disabled. 
      */
    public HtmlFieldsetBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    /**
      * The form attribute is used to explicitly associate the fieldset element with its form owner. The name attribute represents the element's name. 
      */
    public HtmlFieldsetBuilder form(String val) {
        attr("form", val);
        return this;
    }

    public HtmlFieldsetBuilder name(String val) {
        attr("name", val);
        return this;
    }

}
