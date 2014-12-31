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

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

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

    protected void apply(HtmlInputTag input, IFieldDecl fieldDecl) {
        apply(input, fieldDecl, null);
    }

    protected void apply(HtmlInputTag input, IFieldDecl fieldDecl, String suffix) {
        String inputName = fieldDecl.getInputName();
        if (inputName != null) {
            if (suffix != null)
                inputName += suffix;
            input.name(inputName);
        }

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

        if (fieldDecl.getMaxLength() != null)
            input.maxlength(fieldDecl.getMaxLength().toString());

        if (fieldDecl.getTextWidth() != null)
            input.width(fieldDecl.getTextWidth().toString());

        iString placeholder = fieldDecl.getPlaceholder();
        if (placeholder != null)
            input.placeholder(placeholder.toString());
    }

    protected void apply(HtmlSelectTag select, IFieldDecl fieldDecl) {
        apply(select, fieldDecl, null);
    }

    protected void apply(HtmlSelectTag select, IFieldDecl fieldDecl, String suffix) {
        String inputName = fieldDecl.getInputName();
        if (inputName != null) {
            if (suffix != null)
                inputName += suffix;
            select.name(inputName);
        }

        if (fieldDecl.getTextWidth() != null)
            select.size(fieldDecl.getTextWidth().toString());

        // iString placeholder = fieldDecl.getPlaceholder();
    }

}
