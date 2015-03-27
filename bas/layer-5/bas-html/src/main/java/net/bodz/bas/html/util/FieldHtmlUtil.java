package net.bodz.bas.html.util;

import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.dom.tag.HtmlTextareaTag;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.rtx.IOptions;

public class FieldHtmlUtil {

    public static final String NAME_PREFIX = "prefix";

    static String name(IFieldDecl fieldDecl, String prefix, String suffix) {
        String inputName = fieldDecl.getInputName();
        if (inputName == null)
            inputName = fieldDecl.getName();

        if (prefix != null)
            inputName = prefix + inputName;

        if (suffix != null)
            inputName += suffix;

        return inputName;
    }

    public static void apply(HtmlInputTag input, IFieldDecl fieldDecl, IOptions options) {
        apply(input, fieldDecl, options, null);
    }

    public static void apply(HtmlInputTag input, IFieldDecl fieldDecl, IOptions options, String nameSuffix) {
        String inputName = name(fieldDecl, options.<String> get(NAME_PREFIX), nameSuffix);
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

    public static void apply(HtmlTextareaTag textarea, IFieldDecl fieldDecl, IOptions options) {
        apply(textarea, fieldDecl, options, null);
    }

    public static void apply(HtmlTextareaTag textarea, IFieldDecl fieldDecl, IOptions options, String nameSuffix) {
        String inputName = name(fieldDecl, options.<String> get(NAME_PREFIX), nameSuffix);
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

    public static void apply(HtmlSelectTag select, IFieldDecl fieldDecl, IOptions options) {
        apply(select, fieldDecl, options, null);
    }

    public static void apply(HtmlSelectTag select, IFieldDecl fieldDecl, IOptions options, String nameSuffix) {
        String inputName = name(fieldDecl, options.<String> get(NAME_PREFIX), nameSuffix);
        select.name(inputName);

        if (fieldDecl.isReadOnly())
            select.disabled("disabled");

        if (fieldDecl.getTextWidth() != null)
            select.size(fieldDecl.getTextWidth().toString());

        // iString placeholder = fieldDecl.getPlaceholder();
    }

}
