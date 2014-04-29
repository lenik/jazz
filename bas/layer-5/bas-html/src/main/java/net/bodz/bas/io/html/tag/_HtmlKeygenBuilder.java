package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * Constraint validation: The keygen element is barred from constraint validation. 
  */
@SuppressWarnings("unchecked")
class _HtmlKeygenBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlKeygenBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t autofocus(String val) {
        attr("autofocus", val);
        return (self_t) this;
    }

    /**
      * The challenge attribute may be specified. Its value will be packaged with the submitted key. 
      */
    public self_t challenge(String val) {
        attr("challenge", val);
        return (self_t) this;
    }

    public self_t disabled(String val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The form attribute is used to explicitly associate the keygen element with its form owner. The name attribute represents the element's name. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The autofocus attribute controls focus. 
      */
    public self_t form(String val) {
        attr("form", val);
        return (self_t) this;
    }

    /**
      * The keytype attribute is an enumerated attribute. The following table lists the keywords and states for the attribute — the keywords in the left column map to the states listed in the cell in the second column on the same row as the keyword. User agents are not required to support these values, and must only recognize values whose corresponding algorithms they support. 
      */
    public self_t keytype(String val) {
        attr("keytype", val);
        return (self_t) this;
    }

    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

}