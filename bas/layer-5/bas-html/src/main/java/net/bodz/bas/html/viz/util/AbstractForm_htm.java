package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlDivTag;
import net.bodz.bas.html.dom.tag.HtmlFormTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.FieldCategory;
import net.bodz.bas.repr.form.FieldDeclFilters;
import net.bodz.bas.repr.form.FieldDeclGroup;
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
        IHtmlTag formTag = beginForm(ctx, out, ref, options);

        HtmlDivTag fgv = formTag.div().class_("field-groups");
        Collection<FieldDeclGroup> groups = formDecl
                .getFieldGroups(FieldDeclFilters.maxDetailLevel(DetailLevel.EXTEND));

        for (FieldDeclGroup group : groups) {
            if (buildFieldGroup(ctx, fgv, ref, group, options))
                continue;

            FieldCategory category = group.getCategory();
            IHtmlTag catTag = beginCategory(ctx, fgv, category);

            // filter field[category] -> selection
            List<IFieldDecl> selection = new ArrayList<>();
            for (IFieldDecl fieldDecl : group)
                if (filterField(fieldDecl))
                    selection.add(fieldDecl);
            if (selection.isEmpty())
                continue;

            selection = processFieldSelection(ctx, catTag, ref, group, selection, options);
            if (selection != null)
                for (IFieldDecl fieldDecl : selection) {
                    IHtmlTag fieldTag = beginField(ctx, catTag, fieldDecl);
                    fieldBody(ctx, fieldTag, ref, fieldDecl, options);
                    endField(ctx, catTag, fieldTag, fieldDecl);
                }

            endCategory(ctx, fgv, catTag, category);
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

    protected abstract void nullInstance(IHtmlTag out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    protected IHtmlTag beforeForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

    protected IHtmlTag beginForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        HtmlFormTag form = out.form();
        return form;
    }

    protected boolean buildFieldGroup(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> instanceRef, FieldDeclGroup group,
            IOptions options)
            throws ViewBuilderException, IOException {
        return false;
    }

    protected abstract IHtmlTag beginCategory(IHtmlViewContext ctx, IHtmlTag out, FieldCategory category)
            throws ViewBuilderException, IOException;

    protected abstract void endCategory(IHtmlViewContext ctx, IHtmlTag out, IHtmlTag catOut, FieldCategory category);

    protected List<IFieldDecl> processFieldSelection(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> instanceRef,
            FieldDeclGroup group, List<IFieldDecl> selection, IOptions options)
            throws ViewBuilderException, IOException {
        return selection;
    }

    protected abstract IHtmlTag beginField(IHtmlViewContext ctx, IHtmlTag out, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException;

    protected abstract void fieldBody(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> instanceRef, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException;

    protected abstract void endField(IHtmlViewContext ctx, IHtmlTag out, IHtmlTag fieldOut, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException;

    protected void endForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
    }

    protected IHtmlTag afterForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<T> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

}
