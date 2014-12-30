package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public abstract class AbstractFormInput_htm<T>
        extends AbstractHtmlViewBuilder<T> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public AbstractFormInput_htm(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    protected UiPropertyRef<T> apply(HtmlInputTag input, IRefEntry<T> refEntry)
            throws ViewBuilderException {
        if (!(refEntry instanceof UiPropertyRef))
            return null;

        UiPropertyRef<T> propertyRef = (UiPropertyRef<T>) refEntry;
        IFieldDecl fieldDecl = null;
        try {
            fieldDecl = fieldDeclBuilder.build(propertyRef.getProperty());
        } catch (ParseException e) {
            throw new ViewBuilderException("Failed to build field-decl.", e);
        }
        apply(input, fieldDecl);
        return propertyRef;
    }

    protected void apply(HtmlInputTag input, IFieldDecl fieldDecl) {
        if (fieldDecl.getName() != null) {
            // input.id(fieldDecl.getName());
            input.name(fieldDecl.getName());
        }

        if (fieldDecl.getMaxLength() != null)
            input.maxlength(fieldDecl.getMaxLength().toString());

        if (fieldDecl.getTextWidth() != null)
            input.width(fieldDecl.getTextWidth().toString());

        iString placeholder = fieldDecl.getPlaceholder();
        if (placeholder != null)
            input.placeholder(placeholder.toString());
    }

}
