package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.form.MutableFormProperty;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;

@Face("input")
public abstract class AbstractFormInput_htm<T>
        extends AbstractHtmlViewBuilder<T> {

    public AbstractFormInput_htm(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public final IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {

        if (!(ref instanceof UiPropertyRef))
            throw new ViewBuilderException(String.format(//
                    "Unsupported ref type %s used in %s renderer. ", ref.getClass(), getValueType()));

        UiPropertyRef<T> propertyRef = (UiPropertyRef<T>) ref;
        IFormProperty fieldDecl = null;
        try {
            fieldDecl = new MutableFormProperty().populate(propertyRef.getProperty());
        } catch (Exception e) {
            throw new ViewBuilderException("Failed to build field-decl.", e);
        }

        buildHtmlView(ctx, out, propertyRef, fieldDecl);

        return out;
    }

    public abstract void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<T> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException;

}
