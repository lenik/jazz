package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The input element represents a button with no default behavior. A label for the button must be
 * provided in the value attribute, though it may be the empty string. If the element has a value
 * attribute, the button's label must be the value of that attribute; otherwise, it must be the
 * empty string. The element is a button.
 */
public class _HtmlInput<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlInput(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The accept attribute may be specified to provide user agents with a hint of what file types
     * will be accepted.
     */
    public self_t accept(Object val) {
        return attr("accept", val);
    }

    /**
     * The alt attribute provides the textual label for the button for users and user agents who
     * cannot use the image. The alt attribute must be present, and must contain a non-empty string
     * giving the label that would be appropriate for an equivalent button if the image was
     * unavailable.
     */
    public self_t alt(Object val) {
        return attr("alt", val);
    }

    public self_t autocomplete(Object val) {
        return attr("autocomplete", val);
    }

    public self_t autofocus(Object val) {
        return attr("autofocus", val);
    }

    /**
     * The checked content attribute is a boolean attribute that gives the default checkedness of
     * the input element. When the checked content attribute is added, if the control does not have
     * dirty checkedness, the user agent must set the checkedness of the element to true; when the
     * checked content attribute is removed, if the control does not have dirty checkedness, the
     * user agent must set the checkedness of the element to false.
     */
    public self_t checked(Object val) {
        return attr("checked", val);
    }

    public self_t dirname(Object val) {
        return attr("dirname", val);
    }

    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    public self_t form(Object val) {
        return attr("form", val);
    }

    public self_t formaction(Object val) {
        return attr("formaction", val);
    }

    public self_t formenctype(Object val) {
        return attr("formenctype", val);
    }

    public self_t formmethod(Object val) {
        return attr("formmethod", val);
    }

    /**
     * The formnovalidate attribute can be used to make submit buttons that do not trigger the
     * constraint validation.
     */
    public self_t formnovalidate(Object val) {
        return attr("formnovalidate", val);
    }

    public self_t formtarget(Object val) {
        return attr("formtarget", val);
    }

    public self_t height(Object val) {
        return attr("height", val);
    }

    /**
     * The list attribute is used to identify an element that lists predefined options suggested to
     * the user.
     */
    public self_t list(Object val) {
        return attr("list", val);
    }

    public self_t max(Object val) {
        return attr("max", val);
    }

    /**
     * The maxlength attribute, when it applies, is a form control maxlength attribute controlled by
     * the input element's dirty value flag.
     */
    public self_t maxlength(Object val) {
        return attr("maxlength", val);
    }

    /**
     * The min attribute also defines the step base.
     */
    public self_t min(Object val) {
        return attr("min", val);
    }

    /**
     * The minlength attribute, when it applies, is a form control minlength attribute controlled by
     * the input element's dirty value flag.
     */
    public self_t minlength(Object val) {
        return attr("minlength", val);
    }

    /**
     * The multiple attribute is a boolean attribute that indicates whether the user is to be
     * allowed to specify more than one value.
     */
    public self_t multiple(Object val) {
        return attr("multiple", val);
    }

    /**
     * <!-- http://software.hixie.ch/utilities/js/live-dom-viewer/saved/602 -->If the previous state
     * of the element's type attribute put the value IDL attribute in the value mode, and the
     * element's value is not the empty string, and the new state of the element's type attribute
     * puts the value IDL attribute in either the default mode or the default/on mode, then set the
     * element's value content attribute to the element's value. Otherwise, if the previous state of
     * the element's type attribute put the value IDL attribute in any mode other than the value
     * mode, and the new state of the element's type attribute puts the value IDL attribute in the
     * value mode, then set the value of the element to the value of the value content attribute, if
     * there is one, or the empty string otherwise, and then set the control's dirty value flag to
     * false. Update the element's rendering and behavior to the new state's. Invoke the value
     * sanitization algorithm, if one is defined for the type attribute's new state. The name
     * attribute represents the element's name. The dirname attribute controls how the element's
     * directionality is submitted. The disabled attribute is used to make the control
     * non-interactive and to prevent its value from being submitted. The form attribute is used to
     * explicitly associate the input element with its form owner. The autofocus attribute controls
     * focus. The autocomplete attribute controls how the user agent provides autofill behavior.
     */
    public self_t name(Object val) {
        return attr("name", val);
    }

    /**
     * The pattern attribute specifies a regular expression against which the control's value, or,
     * when the multiple attribute applies and is set, the control's values, are to be checked.
     */
    public self_t pattern(Object val) {
        return attr("pattern", val);
    }

    /**
     * The placeholder attribute should not be used as a replacement for a label. For a longer hint
     * or other advisory text, place the text next to the control.
     */
    public self_t placeholder(Object val) {
        return attr("placeholder", val);
    }

    /**
     * The readonly attribute is a boolean attribute that controls whether or not the user can edit
     * the form control. When specified, the element is not mutable.
     */
    public self_t readonly(Object val) {
        return attr("readonly", val);
    }

    /**
     * The required attribute is a boolean attribute. When specified, the element is required.
     */
    public self_t required(Object val) {
        return attr("required", val);
    }

    /**
     * The size attribute, if specified, must have a value that is a valid non-negative integer
     * greater than zero.
     */
    public self_t size(Object val) {
        return attr("size", val);
    }

    /**
     * The image is given by the src attribute. The src attribute must be present, and must contain
     * a valid non-empty URL potentially surrounded by spaces referencing a non-interactive,
     * optionally animated, image resource that is neither paged nor scripted.
     */
    public self_t src(Object val) {
        return attr("src", val);
    }

    /**
     * The step attribute, if specified, must either have a value that is a valid floating-point
     * number that <a data-anolis-xref="rules for parsing floating-point number values"
     * href="infrastructure.html#rules-for-parsing-floating-point-number-values">parses to a number
     * that is greater than zero, or must have a value that is an ASCII case-insensitive match for
     * the string "any".
     */
    public self_t step(Object val) {
        return attr("step", val);
    }

    /**
     * The type attribute controls the data type (and associated control) of the element. It is an
     * enumerated attribute. The following table lists the keywords and states for the attribute —
     * the keywords in the left column map to the states in the cell in the second column on the
     * same row as the keyword.
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

    /**
     * The value attribute, if specified and not empty, must have a value that is a valid simple
     * color.
     */
    public self_t value(Object val) {
        return attr("value", val);
    }

    public self_t width(Object val) {
        return attr("width", val);
    }

}
