package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The input element represents a button with no default behavior. A label for the button must be provided in the value attribute, though it may be the empty string. If the element has a value attribute, the button's label must be the value of that attribute; otherwise, it must be the empty string. The element is a button. 
  */
@SuppressWarnings("unchecked")
class _HtmlInputTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlInputTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The accept attribute may be specified to provide user agents with a hint of what file types will be accepted. 
      */
    public self_t accept(Object val) {
        attr("accept", val);
        return (self_t) this;
    }

    /**
      * The alt attribute provides the textual label for the button for users and user agents who cannot use the image. The alt attribute must be present, and must contain a non-empty string giving the label that would be appropriate for an equivalent button if the image was unavailable. 
      */
    public self_t alt(Object val) {
        attr("alt", val);
        return (self_t) this;
    }

    public self_t autocomplete(Object val) {
        attr("autocomplete", val);
        return (self_t) this;
    }

    public self_t autofocus(Object val) {
        attr("autofocus", val);
        return (self_t) this;
    }

    /**
      * The checked content attribute is a boolean attribute that gives the default checkedness of the input element. When the checked content attribute is added, if the control does not have dirty checkedness, the user agent must set the checkedness of the element to true; when the checked content attribute is removed, if the control does not have dirty checkedness, the user agent must set the checkedness of the element to false. 
      */
    public self_t checked(Object val) {
        attr("checked", val);
        return (self_t) this;
    }

    public self_t dirname(Object val) {
        attr("dirname", val);
        return (self_t) this;
    }

    public self_t disabled(Object val) {
        attr("disabled", val);
        return (self_t) this;
    }

    public self_t form(Object val) {
        attr("form", val);
        return (self_t) this;
    }

    public self_t formaction(Object val) {
        attr("formaction", val);
        return (self_t) this;
    }

    public self_t formenctype(Object val) {
        attr("formenctype", val);
        return (self_t) this;
    }

    public self_t formmethod(Object val) {
        attr("formmethod", val);
        return (self_t) this;
    }

    /**
      * The formnovalidate attribute can be used to make submit buttons that do not trigger the constraint validation. 
      */
    public self_t formnovalidate(Object val) {
        attr("formnovalidate", val);
        return (self_t) this;
    }

    public self_t formtarget(Object val) {
        attr("formtarget", val);
        return (self_t) this;
    }

    public self_t height(Object val) {
        attr("height", val);
        return (self_t) this;
    }

    /**
      * The list attribute is used to identify an element that lists predefined options suggested to the user. 
      */
    public self_t list(Object val) {
        attr("list", val);
        return (self_t) this;
    }

    public self_t max(Object val) {
        attr("max", val);
        return (self_t) this;
    }

    /**
      * The maxlength attribute, when it applies, is a form control maxlength attribute controlled by the input element's dirty value flag. 
      */
    public self_t maxlength(Object val) {
        attr("maxlength", val);
        return (self_t) this;
    }

    /**
      * The min attribute also defines the step base. 
      */
    public self_t min(Object val) {
        attr("min", val);
        return (self_t) this;
    }

    /**
      * The minlength attribute, when it applies, is a form control minlength attribute controlled by the input element's dirty value flag. 
      */
    public self_t minlength(Object val) {
        attr("minlength", val);
        return (self_t) this;
    }

    /**
      * The multiple attribute is a boolean attribute that indicates whether the user is to be allowed to specify more than one value. 
      */
    public self_t multiple(Object val) {
        attr("multiple", val);
        return (self_t) this;
    }

    /**
      * <!-- http://software.hixie.ch/utilities/js/live-dom-viewer/saved/602 -->If the previous state of the element's type attribute put the value IDL attribute in the value mode, and the element's value is not the empty string, and the new state of the element's type attribute puts the value IDL attribute in either the default mode or the default/on mode, then set the element's value content attribute to the element's value. Otherwise, if the previous state of the element's type attribute put the value IDL attribute in any mode other than the value mode, and the new state of the element's type attribute puts the value IDL attribute in the value mode, then set the value of the element to the value of the value content attribute, if there is one, or the empty string otherwise, and then set the control's dirty value flag to false. Update the element's rendering and behavior to the new state's. Invoke the value sanitization algorithm, if one is defined for the type attribute's new state. The name attribute represents the element's name. The dirname attribute controls how the element's directionality is submitted. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The form attribute is used to explicitly associate the input element with its form owner. The autofocus attribute controls focus. The autocomplete attribute controls how the user agent provides autofill behavior. 
      */
    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The pattern attribute specifies a regular expression against which the control's value, or, when the multiple attribute applies and is set, the control's values, are to be checked. 
      */
    public self_t pattern(Object val) {
        attr("pattern", val);
        return (self_t) this;
    }

    /**
      * The placeholder attribute should not be used as a replacement for a label. For a longer hint or other advisory text, place the text next to the control. 
      */
    public self_t placeholder(Object val) {
        attr("placeholder", val);
        return (self_t) this;
    }

    /**
      * The readonly attribute is a boolean attribute that controls whether or not the user can edit the form control. When specified, the element is not mutable. 
      */
    public self_t readonly(Object val) {
        attr("readonly", val);
        return (self_t) this;
    }

    /**
      * The required attribute is a boolean attribute. When specified, the element is required. 
      */
    public self_t required(Object val) {
        attr("required", val);
        return (self_t) this;
    }

    /**
      * The size attribute, if specified, must have a value that is a valid non-negative integer greater than zero. 
      */
    public self_t size(Object val) {
        attr("size", val);
        return (self_t) this;
    }

    /**
      * The image is given by the src attribute. The src attribute must be present, and must contain a valid non-empty URL potentially surrounded by spaces referencing a non-interactive, optionally animated, image resource that is neither paged nor scripted. 
      */
    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The step attribute, if specified, must either have a value that is a valid floating-point number that <a data-anolis-xref="rules for parsing floating-point number values" href="infrastructure.html#rules-for-parsing-floating-point-number-values">parses to a number that is greater than zero, or must have a value that is an ASCII case-insensitive match for the string "any". 
      */
    public self_t step(Object val) {
        attr("step", val);
        return (self_t) this;
    }

    /**
      * The type attribute controls the data type (and associated control) of the element. It is an enumerated attribute. The following table lists the keywords and states for the attribute — the keywords in the left column map to the states in the cell in the second column on the same row as the keyword. 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The value attribute, if specified and not empty, must have a value that is a valid simple color. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

    public self_t width(Object val) {
        attr("width", val);
        return (self_t) this;
    }

}
