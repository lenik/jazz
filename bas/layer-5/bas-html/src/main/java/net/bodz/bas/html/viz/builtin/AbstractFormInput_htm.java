package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractFormInput_htm<T>
        extends AbstractHtmlViewBuilder<T> {

    private static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public AbstractFormInput_htm(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public final void buildHtmlViewStart(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref)
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

        buildHtmlView(ctx, out, propertyRef, fieldDecl);
    }

    public abstract void buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<T> ref,
            IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException;

}
