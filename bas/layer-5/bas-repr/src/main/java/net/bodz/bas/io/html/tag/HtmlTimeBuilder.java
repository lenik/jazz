package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The time element can be used to encode dates, for example in microformats. The following shows a hypothetical way of encoding an event using a variant on hCalendar that uses the time element: 
  */
public class HtmlTimeBuilder
        extends DecoratedHtmlTagBuilder<HtmlTimeBuilder> {

    public HtmlTimeBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The datetime attribute may be present. If present, its value must be a representation of the element's contents in a machine-readable format. 
      */
    public HtmlTimeBuilder datetime(String val) {
        attr("datetime", val);
        return this;
    }

}
