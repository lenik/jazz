package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractFormInput_htm<T>
        extends AbstractHtmlViewBuilder<T> {

    public static final String NAME_PREFIX = "prefix";
    private static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public AbstractFormInput_htm(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public final IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {

        if (!(ref instanceof UiPropertyRef))
            throw new ViewBuilderException("Unsupported ref type: " + ref.getClass());

        UiPropertyRef<T> propertyRef = (UiPropertyRef<T>) ref;
        IFieldDecl fieldDecl = null;
        try {
            fieldDecl = fieldDeclBuilder.build(propertyRef.getProperty());
        } catch (ParseException e) {
            throw new ViewBuilderException("Failed to build field-decl.", e);
        }

        return buildHtmlView(ctx, out, propertyRef, fieldDecl, options);
    }

    public abstract IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<T> ref,
            IFieldDecl fieldDecl, IOptions options)
            throws ViewBuilderException, IOException;

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

    protected void apply(HtmlInputTag input, IFieldDecl fieldDecl, IOptions options) {
        apply(input, fieldDecl, options, null);
    }

    protected void apply(HtmlInputTag input, IFieldDecl fieldDecl, IOptions options, String nameSuffix) {
        String inputName = name(fieldDecl, options.<String> get(NAME_PREFIX), nameSuffix);
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

    protected void apply(HtmlSelectTag select, IFieldDecl fieldDecl, IOptions options) {
        apply(select, fieldDecl, options, null);
    }

    protected void apply(HtmlSelectTag select, IFieldDecl fieldDecl, IOptions options, String nameSuffix) {
        String inputName = name(fieldDecl, options.<String> get(NAME_PREFIX), nameSuffix);
        select.name(inputName);

        if (fieldDecl.getTextWidth() != null)
            select.size(fieldDecl.getTextWidth().toString());

        // iString placeholder = fieldDecl.getPlaceholder();
    }

}
