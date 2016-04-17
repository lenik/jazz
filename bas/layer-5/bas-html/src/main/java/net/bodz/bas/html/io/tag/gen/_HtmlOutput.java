package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The output element represents the result of a calculation or user action.
 */
public class _HtmlOutput<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlOutput(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The for content attribute allows an explicit relationship to be made between the result of a
     * calculation and the elements that represent the values that went into the calculation or that
     * otherwise influenced the calculation. The for attribute, if specified, must contain a string
     * consisting of an unordered set of unique space-separated tokens that are case-sensitive, each
     * of which must have the value of an ID of an element in the same Document.
     */
    public self_t for_(Object val) {
        return attr("for", val);
    }

    /**
     * The form attribute is used to explicitly associate the output element with its form owner.
     * The name attribute represents the element's name.
     */
    public self_t form(Object val) {
        return attr("form", val);
    }

    public self_t name(Object val) {
        return attr("name", val);
    }

}
