package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The meter element also does not represent a scalar value of arbitrary range — for example, it would be wrong to use this to report a weight, or height, unless there is a known maximum value. 
  */
@SuppressWarnings("unchecked")
class _HtmlMeterBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlMeterBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * Authoring requirements: The value attribute must be specified. The value, min, low, high, max, and optimum attributes, when present, must have values that are valid floating-point numbers. 
      */
    public self_t value(String val) {
        attr("value", val);
        return (self_t) this;
    }

    /**
      * The min attribute specifies the lower bound of the range, and the max attribute specifies the upper bound. The value attribute specifies the value to have the gauge indicate as the "measured" value. 
      */
    public self_t min(String val) {
        attr("min", val);
        return (self_t) this;
    }

    public self_t max(String val) {
        attr("max", val);
        return (self_t) this;
    }

    /**
      * The other three attributes can be used to segment the gauge's range into "low", "medium", and "high" parts, and to indicate which part of the gauge is the "optimum" part. The low attribute specifies the range that is considered to be the "low" part, and the high attribute specifies the range that is considered to be the "high" part. The optimum attribute gives the position that is "optimum"; if that is higher than the "high" value then this indicates that the higher the value, the better; if it's lower than the "low" mark then it indicates that lower values are better, and naturally if it is in between then it indicates that neither high nor low values are good. 
      */
    public self_t low(String val) {
        attr("low", val);
        return (self_t) this;
    }

    public self_t high(String val) {
        attr("high", val);
        return (self_t) this;
    }

    public self_t optimum(String val) {
        attr("optimum", val);
        return (self_t) this;
    }

}