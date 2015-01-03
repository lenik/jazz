package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlFormTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.FieldCategory;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractForm_htm<T>
        extends AbstractHtmlViewBuilder<T> {

    public AbstractForm_htm(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        Object o = ref.get();
        if (o == null) {
            nullInstance(out, ref);
            return out;
        }

        beforeForm(ctx, out, ref, options);

        IFormDecl formDecl = IFormDecl.fn.forClass(ref.getValueType());
        HtmlFormTag formTag = beginForm(ctx, out, ref, options);

        Map<FieldCategory, Collection<IFieldDecl>> categories = FieldCategory.group(//
                formDecl.getFieldDefs(DetailLevel.EXTEND));

        for (FieldCategory category : categories.keySet()) {

            // filter field[category] -> selection
            List<IFieldDecl> selection = new ArrayList<>();
            for (IFieldDecl fieldDecl : categories.get(category))
                if (filterField(fieldDecl))
                    selection.add(fieldDecl);
            if (selection.isEmpty())
                continue;

            IHtmlTag catTag = beginCategory(formTag, category);

            for (IFieldDecl fieldDecl : selection) {
                IHtmlTag fieldTag = beginField(catTag, fieldDecl);

                fieldBody(ctx, fieldTag, ref, fieldDecl, options);

                endField(fieldTag, fieldDecl);
            }

            endCategory(catTag, category);
        }

        endForm(ctx, formTag, ref, options);
        out = afterForm(ctx, out, ref, options);
        return out;
    }

    protected boolean filterField(IFieldDecl formField) {
        IProperty property = formField.getProperty();
        if (property == null)
            return false;

        if (!property.isReadable())
            return false;

        return true;
    }

    protected abstract void nullInstance(IHtmlTag out, IUiRef<T> ref);

    protected abstract IHtmlTag beginCategory(IHtmlTag out, FieldCategory category)
            throws ViewBuilderException;

    protected abstract void endCategory(IHtmlTag out, FieldCategory category);

    protected abstract IHtmlTag beginField(IHtmlTag out, IFieldDecl fieldDecl)
            throws ViewBuilderException;

    protected abstract void fieldBody(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> instanceRef, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException;

    protected abstract void endField(IHtmlTag out, IFieldDecl fieldDecl);

    protected IHtmlTag beforeForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

    protected HtmlFormTag beginForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HtmlFormTag form = out.form().method("post");
        return form;
    }

    protected void endForm(IHtmlViewContext ctx, HtmlFormTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
    }

    protected IHtmlTag afterForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

}
