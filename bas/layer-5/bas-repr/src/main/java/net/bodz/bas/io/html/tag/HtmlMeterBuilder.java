package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The meter element also does not represent a scalar value of arbitrary range — for example, it would be wrong to use this to report a weight, or height, unless there is a known maximum value. 
  */
public class HtmlMeterBuilder
        extends DecoratedHtmlTagBuilder<HtmlMeterBuilder> {

    public HtmlMeterBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * Authoring requirements: The value attribute must be specified. The value, min, low, high, max, and optimum attributes, when present, must have values that are valid floating-point numbers. 
      */
    public HtmlMeterBuilder value(String val) {
        attr("value", val);
        return this;
    }

    /**
      * The min attribute specifies the lower bound of the range, and the max attribute specifies the upper bound. The value attribute specifies the value to have the gauge indicate as the "measured" value. 
      */
    public HtmlMeterBuilder min(String val) {
        attr("min", val);
        return this;
    }

    public HtmlMeterBuilder max(String val) {
        attr("max", val);
        return this;
    }

    /**
      * The other three attributes can be used to segment the gauge's range into "low", "medium", and "high" parts, and to indicate which part of the gauge is the "optimum" part. The low attribute specifies the range that is considered to be the "low" part, and the high attribute specifies the range that is considered to be the "high" part. The optimum attribute gives the position that is "optimum"; if that is higher than the "high" value then this indicates that the higher the value, the better; if it's lower than the "low" mark then it indicates that lower values are better, and naturally if it is in between then it indicates that neither high nor low values are good. 
      */
    public HtmlMeterBuilder low(String val) {
        attr("low", val);
        return this;
    }

    public HtmlMeterBuilder high(String val) {
        attr("high", val);
        return this;
    }

    public HtmlMeterBuilder optimum(String val) {
        attr("optimum", val);
        return this;
    }

}
