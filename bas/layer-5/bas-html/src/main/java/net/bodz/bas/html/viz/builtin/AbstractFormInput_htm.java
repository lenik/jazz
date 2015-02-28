package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHttpViewBuilder;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractFormInput_htm<T>
        extends AbstractHttpViewBuilder<T> {

    private static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public AbstractFormInput_htm(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public final IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
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

    public abstract IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, UiPropertyRef<T> ref,
            IFieldDecl fieldDecl, IOptions options)
            throws ViewBuilderException, IOException;

}
