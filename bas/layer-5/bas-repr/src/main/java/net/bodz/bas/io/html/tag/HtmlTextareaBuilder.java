package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The textarea element represents a multiline plain text edit control for the element's raw value. The contents of the control represent the control's default value. 
  */
public class HtmlTextareaBuilder
        extends DecoratedHtmlTagBuilder<HtmlTextareaBuilder> {

    public HtmlTextareaBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlTextareaBuilder autocomplete(String val) {
        attr("autocomplete", val);
        return this;
    }

    public HtmlTextareaBuilder autofocus(String val) {
        attr("autofocus", val);
        return this;
    }

    /**
      * The cols, placeholder, required, rows, and wrap attributes must reflect the respective content attributes of the same name. The cols and rows attributes are limited to only non-negative numbers greater than zero. The cols attribute's default value is 20. The rows attribute's default value is 2. The dirName IDL attribute must reflect the dirname content attribute. The maxLength IDL attribute must reflect the maxlength content attribute, limited to only non-negative numbers. The minLength IDL attribute must reflect the minlength content attribute, limited to only non-negative numbers. The readOnly IDL attribute must reflect the readonly content attribute. 
      */
    public HtmlTextareaBuilder cols(String val) {
        attr("cols", val);
        return this;
    }

    public HtmlTextareaBuilder dirname(String val) {
        attr("dirname", val);
        return this;
    }

    public HtmlTextareaBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    public HtmlTextareaBuilder form(String val) {
        attr("form", val);
        return this;
    }

    /**
      * Replace every occurrence of a "CR" (U+000D) character not followed by a "LF" (U+000A) character, and every occurrence of a "LF" (U+000A) character not preceded by a "CR" (U+000D) character, by a two-character string consisting of a U+000D CARRIAGE RETURN "CRLF" (U+000A) character pair. If the element's wrap attribute is in the Hard state, insert U+000D CARRIAGE RETURN "CRLF" (U+000A) character pairs into the string using a UA-defined algorithm so that each line has no more than character width characters. For the purposes of this requirement, lines are delimited by the start of the string, the end of the string, and U+000D CARRIAGE RETURN "CRLF" (U+000A) character pairs. The maxlength attribute is a form control maxlength attribute controlled by the textarea element's dirty value flag. 
      */
    public HtmlTextareaBuilder maxlength(String val) {
        attr("maxlength", val);
        return this;
    }

    /**
      * The minlength attribute is a form control minlength attribute controlled by the textarea element's dirty value flag. 
      */
    public HtmlTextareaBuilder minlength(String val) {
        attr("minlength", val);
        return this;
    }

    /**
      * The name attribute represents the element's name. The dirname attribute controls how the element's directionality is submitted. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The form attribute is used to explicitly associate the textarea element with its form owner. The autofocus attribute controls focus. The autocomplete attribute controls how the user agent provides autofill behavior. 
      */
    public HtmlTextareaBuilder name(String val) {
        attr("name", val);
        return this;
    }

    /**
      * The placeholder attribute should not be used as a replacement for a label. For a longer hint or other advisory text, place the text next to the control. 
      */
    public HtmlTextareaBuilder placeholder(String val) {
        attr("placeholder", val);
        return this;
    }

    /**
      * The readonly attribute is a boolean attribute used to control whether the text can be edited by the user or not. 
      */
    public HtmlTextareaBuilder readonly(String val) {
        attr("readonly", val);
        return this;
    }

    /**
      * The required attribute is a boolean attribute. When specified, the user will be required to enter a value before submitting the form. 
      */
    public HtmlTextareaBuilder required(String val) {
        attr("required", val);
        return this;
    }

    /**
      * The rows attribute specifies the number of lines to show. If the rows attribute is specified, its value must be a valid non-negative integer greater than zero. If applying the rules for parsing non-negative integers to the attribute's value results in a number greater than zero, then the element's character height is that value; otherwise, it is 2. 
      */
    public HtmlTextareaBuilder rows(String val) {
        attr("rows", val);
        return this;
    }

    /**
      * The wrap attribute is an enumerated attribute with two keywords and states: the soft keyword which maps to the Soft state, and the hard keyword which maps to the Hard state. The missing value default is the Soft state. 
      */
    public HtmlTextareaBuilder wrap(String val) {
        attr("wrap", val);
        return this;
    }

}
