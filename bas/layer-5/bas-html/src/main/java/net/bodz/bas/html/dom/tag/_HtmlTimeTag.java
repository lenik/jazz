package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The time element can be used to encode dates, for example in microformats. The following shows a hypothetical way of encoding an event using a variant on hCalendar that uses the time element: 
  */
@SuppressWarnings("unchecked")
class _HtmlTimeTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTimeTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The datetime attribute may be present. If present, its value must be a representation of the element's contents in a machine-readable format. 
      */
    public self_t datetime(Object val) {
        attr("datetime", val);
        return (self_t) this;
    }

}
