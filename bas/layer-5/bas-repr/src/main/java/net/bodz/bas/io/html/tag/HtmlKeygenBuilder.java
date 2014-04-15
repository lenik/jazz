package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * Constraint validation: The keygen element is barred from constraint validation. 
  */
public class HtmlKeygenBuilder
        extends DecoratedHtmlTagBuilder<HtmlKeygenBuilder> {

    public HtmlKeygenBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlKeygenBuilder autofocus(String val) {
        attr("autofocus", val);
        return this;
    }

    /**
      * The challenge attribute may be specified. Its value will be packaged with the submitted key. 
      */
    public HtmlKeygenBuilder challenge(String val) {
        attr("challenge", val);
        return this;
    }

    public HtmlKeygenBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    /**
      * The form attribute is used to explicitly associate the keygen element with its form owner. The name attribute represents the element's name. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The autofocus attribute controls focus. 
      */
    public HtmlKeygenBuilder form(String val) {
        attr("form", val);
        return this;
    }

    /**
      * The keytype attribute is an enumerated attribute. The following table lists the keywords and states for the attribute — the keywords in the left column map to the states listed in the cell in the second column on the same row as the keyword. User agents are not required to support these values, and must only recognize values whose corresponding algorithms they support. 
      */
    public HtmlKeygenBuilder keytype(String val) {
        attr("keytype", val);
        return this;
    }

    public HtmlKeygenBuilder name(String val) {
        attr("name", val);
        return this;
    }

}
