package net.bodz.bas.html.util;

import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.dom.tag.HtmlTextareaTag;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.repr.form.IFieldDecl;

public class FieldHtmlUtil {

    static String _name(IFieldDecl fieldDecl, String prefix, String suffix) {
        String inputName = fieldDecl.getInputName();
        if (inputName == null)
            inputName = fieldDecl.getName();

        if (prefix != null)
            inputName = prefix + inputName;

        if (suffix != null)
            inputName += suffix;

        return inputName;
    }

    public static void apply(HtmlInputTag input, IFieldDecl fieldDecl) {
        apply(input, fieldDecl, null, null);
    }

    public static void apply(HtmlInputTag input, IFieldDecl fieldDecl, String namePrefix, String nameSuffix) {
        String inputName = _name(fieldDecl, namePrefix, nameSuffix);
        // input.id(inputName);
        input.name(inputName);

        String face = fieldDecl.getFace();
        if (face != null)
            switch (fieldDecl.getFace()) {
            case "text":
            case "password":
            case "search":
            case "email":
            case "url":
            case "tel":
            case "number":
            case "range":
            case "date":
            case "month":
            case "week":
            case "time":
            case "datetime":
            case "datetime-local":
            case "color":
                input.type(fieldDecl.getFace());
            }

        if (!fieldDecl.isEnabled())
            input.disabled("disabled");

        if (fieldDecl.isReadOnly())
            input.readonly("readonly");

        Integer maxLength = fieldDecl.getMaxLength();
        Integer textWidth = fieldDecl.getTextWidth();

        if (maxLength != null) {
            input.maxlength(maxLength.toString());
            if (textWidth == null)
                textWidth = Math.min(maxLength, 40);
        }

        if (textWidth != null)
            input.size(textWidth.toString());

        iString placeholder = fieldDecl.getPlaceholder();
        if (placeholder != null)
            input.placeholder(placeholder.toString());
    }

    public static void apply(HtmlTextareaTag textarea, IFieldDecl fieldDecl) {
        apply(textarea, fieldDecl, null, null);
    }

    public static void apply(HtmlTextareaTag textarea, IFieldDecl fieldDecl, String namePrefix, String nameSuffix) {
        String inputName = _name(fieldDecl, namePrefix, nameSuffix);
        textarea.name(inputName);

        if (fieldDecl.isReadOnly())
            textarea.readonly("readonly");

        Integer maxLength = fieldDecl.getMaxLength();
        Integer textWidth = fieldDecl.getTextWidth();

        if (maxLength != null) {
            textarea.maxlength(maxLength.toString());
            if (textWidth == null)
                textWidth = Math.min(maxLength, 40);
        }

        if (textWidth != null)
            textarea.cols(textWidth.toString());

        iString placeholder = fieldDecl.getPlaceholder();
        if (placeholder != null)
            textarea.placeholder(placeholder.toString());
    }

    public static void apply(HtmlSelectTag select, IFieldDecl fieldDecl) {
        apply(select, fieldDecl, null, null);
    }

    public static void apply(HtmlSelectTag select, IFieldDecl fieldDecl, String namePrefix, String nameSuffix) {
        String inputName = _name(fieldDecl, namePrefix, nameSuffix);
        select.name(inputName);

        if (fieldDecl.isReadOnly())
            select.disabled("disabled");

        if (fieldDecl.getTextWidth() != null)
            select.size(fieldDecl.getTextWidth().toString());

        // iString placeholder = fieldDecl.getPlaceholder();
    }

}
