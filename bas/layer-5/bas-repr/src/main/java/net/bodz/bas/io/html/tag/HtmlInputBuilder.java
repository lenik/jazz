package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The input element represents a button with no default behavior. A label for the button must be provided in the value attribute, though it may be the empty string. If the element has a value attribute, the button's label must be the value of that attribute; otherwise, it must be the empty string. The element is a button. 
  */
public class HtmlInputBuilder
        extends DecoratedHtmlTagBuilder<HtmlInputBuilder> {

    public HtmlInputBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The accept attribute may be specified to provide user agents with a hint of what file types will be accepted. 
      */
    public HtmlInputBuilder accept(String val) {
        attr("accept", val);
        return this;
    }

    /**
      * The alt attribute provides the textual label for the button for users and user agents who cannot use the image. The alt attribute must be present, and must contain a non-empty string giving the label that would be appropriate for an equivalent button if the image was unavailable. 
      */
    public HtmlInputBuilder alt(String val) {
        attr("alt", val);
        return this;
    }

    public HtmlInputBuilder autocomplete(String val) {
        attr("autocomplete", val);
        return this;
    }

    public HtmlInputBuilder autofocus(String val) {
        attr("autofocus", val);
        return this;
    }

    /**
      * The checked content attribute is a boolean attribute that gives the default checkedness of the input element. When the checked content attribute is added, if the control does not have dirty checkedness, the user agent must set the checkedness of the element to true; when the checked content attribute is removed, if the control does not have dirty checkedness, the user agent must set the checkedness of the element to false. 
      */
    public HtmlInputBuilder checked(String val) {
        attr("checked", val);
        return this;
    }

    public HtmlInputBuilder dirname(String val) {
        attr("dirname", val);
        return this;
    }

    public HtmlInputBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    public HtmlInputBuilder form(String val) {
        attr("form", val);
        return this;
    }

    public HtmlInputBuilder formaction(String val) {
        attr("formaction", val);
        return this;
    }

    public HtmlInputBuilder formenctype(String val) {
        attr("formenctype", val);
        return this;
    }

    public HtmlInputBuilder formmethod(String val) {
        attr("formmethod", val);
        return this;
    }

    /**
      * The formnovalidate attribute can be used to make submit buttons that do not trigger the constraint validation. 
      */
    public HtmlInputBuilder formnovalidate(String val) {
        attr("formnovalidate", val);
        return this;
    }

    public HtmlInputBuilder formtarget(String val) {
        attr("formtarget", val);
        return this;
    }

    public HtmlInputBuilder height(String val) {
        attr("height", val);
        return this;
    }

    /**
      * The list attribute is used to identify an element that lists predefined options suggested to the user. 
      */
    public HtmlInputBuilder list(String val) {
        attr("list", val);
        return this;
    }

    public HtmlInputBuilder max(String val) {
        attr("max", val);
        return this;
    }

    /**
      * The maxlength attribute, when it applies, is a form control maxlength attribute controlled by the input element's dirty value flag. 
      */
    public HtmlInputBuilder maxlength(String val) {
        attr("maxlength", val);
        return this;
    }

    /**
      * The min attribute also defines the step base. 
      */
    public HtmlInputBuilder min(String val) {
        attr("min", val);
        return this;
    }

    /**
      * The minlength attribute, when it applies, is a form control minlength attribute controlled by the input element's dirty value flag. 
      */
    public HtmlInputBuilder minlength(String val) {
        attr("minlength", val);
        return this;
    }

    /**
      * The multiple attribute is a boolean attribute that indicates whether the user is to be allowed to specify more than one value. 
      */
    public HtmlInputBuilder multiple(String val) {
        attr("multiple", val);
        return this;
    }

    /**
      * <!-- http://software.hixie.ch/utilities/js/live-dom-viewer/saved/602 -->If the previous state of the element's type attribute put the value IDL attribute in the value mode, and the element's value is not the empty string, and the new state of the element's type attribute puts the value IDL attribute in either the default mode or the default/on mode, then set the element's value content attribute to the element's value. Otherwise, if the previous state of the element's type attribute put the value IDL attribute in any mode other than the value mode, and the new state of the element's type attribute puts the value IDL attribute in the value mode, then set the value of the element to the value of the value content attribute, if there is one, or the empty string otherwise, and then set the control's dirty value flag to false. Update the element's rendering and behavior to the new state's. Invoke the value sanitization algorithm, if one is defined for the type attribute's new state. The name attribute represents the element's name. The dirname attribute controls how the element's directionality is submitted. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The form attribute is used to explicitly associate the input element with its form owner. The autofocus attribute controls focus. The autocomplete attribute controls how the user agent provides autofill behavior. 
      */
    public HtmlInputBuilder name(String val) {
        attr("name", val);
        return this;
    }

    /**
      * The pattern attribute specifies a regular expression against which the control's value, or, when the multiple attribute applies and is set, the control's values, are to be checked. 
      */
    public HtmlInputBuilder pattern(String val) {
        attr("pattern", val);
        return this;
    }

    /**
      * The placeholder attribute should not be used as a replacement for a label. For a longer hint or other advisory text, place the text next to the control. 
      */
    public HtmlInputBuilder placeholder(String val) {
        attr("placeholder", val);
        return this;
    }

    /**
      * The readonly attribute is a boolean attribute that controls whether or not the user can edit the form control. When specified, the element is not mutable. 
      */
    public HtmlInputBuilder readonly(String val) {
        attr("readonly", val);
        return this;
    }

    /**
      * The required attribute is a boolean attribute. When specified, the element is required. 
      */
    public HtmlInputBuilder required(String val) {
        attr("required", val);
        return this;
    }

    /**
      * The size attribute, if specified, must have a value that is a valid non-negative integer greater than zero. 
      */
    public HtmlInputBuilder size(String val) {
        attr("size", val);
        return this;
    }

    /**
      * The image is given by the src attribute. The src attribute must be present, and must contain a valid non-empty URL potentially surrounded by spaces referencing a non-interactive, optionally animated, image resource that is neither paged nor scripted. 
      */
    public HtmlInputBuilder src(String val) {
        attr("src", val);
        return this;
    }

    /**
      * The step attribute, if specified, must either have a value that is a valid floating-point number that <a data-anolis-xref="rules for parsing floating-point number values" href="infrastructure.html#rules-for-parsing-floating-point-number-values">parses to a number that is greater than zero, or must have a value that is an ASCII case-insensitive match for the string "any". 
      */
    public HtmlInputBuilder step(String val) {
        attr("step", val);
        return this;
    }

    /**
      * The type attribute controls the data type (and associated control) of the element. It is an enumerated attribute. The following table lists the keywords and states for the attribute — the keywords in the left column map to the states in the cell in the second column on the same row as the keyword. 
      */
    public HtmlInputBuilder type(String val) {
        attr("type", val);
        return this;
    }

    /**
      * The value attribute, if specified and not empty, must have a value that is a valid simple color. 
      */
    public HtmlInputBuilder value(String val) {
        attr("value", val);
        return this;
    }

    public HtmlInputBuilder width(String val) {
        attr("width", val);
        return this;
    }

}
