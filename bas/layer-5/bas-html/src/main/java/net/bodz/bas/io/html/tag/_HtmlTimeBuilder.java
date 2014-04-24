package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The time element can be used to encode dates, for example in microformats. The following shows a hypothetical way of encoding an event using a variant on hCalendar that uses the time element: 
  */
@SuppressWarnings("unchecked")
class _HtmlTimeBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlTimeBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The datetime attribute may be present. If present, its value must be a representation of the element's contents in a machine-readable format. 
      */
    public self_t datetime(String val) {
        attr("datetime", val);
        return (self_t) this;
    }

}
