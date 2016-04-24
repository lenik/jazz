package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The time element can be used to encode dates, for example in microformats. The following shows a hypothetical way of encoding an event using a variant on hCalendar that uses the time element: 
  */
@SuppressWarnings("unchecked")
public class _MutableTime<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableTime(IHtmlTag parent) {
        super(parent, "time");
    }

    /**
      * The datetime attribute may be present. If present, its value must be a representation of the element's contents in a machine-readable format. 
      */
    public self_t datetime(Object val) {
        attr("datetime", val);
        return (self_t) this;
    }

}
