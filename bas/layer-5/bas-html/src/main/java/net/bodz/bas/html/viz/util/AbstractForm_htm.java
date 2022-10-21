package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlDiv;
import net.bodz.bas.html.io.tag.HtmlForm;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.repr.form.PropertyCategory;
import net.bodz.bas.repr.form.PropertyFilters;
import net.bodz.bas.repr.form.PropertyGroup;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.form.IFormDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractForm_htm<T>
        extends AbstractHtmlViewBuilder<T> {

    static final String ATTRIBUTE_MARKSET = ".markSet";

    public AbstractForm_htm(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        Object o = ref.get();
        if (o == null) {
            nullInstance(out, ref);
            return out;
        }

        beforeForm(ctx, out, ref);
        buildForm(ctx, out, ref);
        afterForm(ctx, out, ref);

        extras(ctx, out, ref);
        return out;
    }

    protected IHtmlOut buildForm(IHtmlViewContext ctx, IHtmlOut out, IUiRef<?> ref)
            throws ViewBuilderException, IOException {

        IFormDecl formDecl = IFormDecl.fn.forClass(ref.getValueType());
        IHtmlOut formTag = beginForm(ctx, out, ref);

        HtmlDiv fgvOut = formTag.div().class_("field-groups");
        Collection<PropertyGroup> groups = formDecl
                .getPropertyGroups(PropertyFilters.maxDetailLevel(DetailLevel.DETAIL));

// MarkSet<Object> markSet = ctx.getAttribute(ATTRIBUTE_MARKSET);
// if (markSet == null)
// ctx.setAttribute(ATTRIBUTE_MARKSET, markSet = new MarkSet<>());
// markSet.add(ref);

        for (PropertyGroup group : groups) {
            if (overrideFieldGroup(ctx, fgvOut, ref, group))
                continue;

            // filter field[category] -> selection
            List<IFormProperty> selection = new ArrayList<IFormProperty>();
            for (IFormProperty fieldDecl : group)
                if (filterField(fieldDecl))
                    selection.add(fieldDecl);
            if (selection.isEmpty())
                continue;

            selection = overrideFieldSelection(ref, group, selection);
            if (selection == null || selection.isEmpty())
                continue;

            PropertyCategory category = group.getCategory();
            IHtmlOut catTag = beginCategory(ctx, fgvOut, category);

            for (IFormProperty fieldDecl : selection) {
                IHtmlOut fieldTag = beginField(ctx, catTag, fieldDecl);
                fieldBody(ctx, fieldTag, ref, fieldDecl);
                endField(ctx, catTag, fieldTag, fieldDecl);
            }

            endCategory(ctx, fgvOut, catTag, category);
        }

        endForm(ctx, formTag, ref);
        return out;
    }

    protected boolean filterField(IFormProperty formField) {
        IProperty property = formField.getProperty();
        if (property == null)
            return false;

        if (!property.isReadable())
            return false;

        return true;
    }

    protected abstract void nullInstance(IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException;

    protected IHtmlOut beforeForm(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return out;
    }

    protected IHtmlOut beginForm(IHtmlViewContext ctx, IHtmlOut out, IUiRef<?> ref)
            throws ViewBuilderException, IOException {
        HtmlForm form = out.form();
        return form;
    }

    /**
     * @return Whether the processing of the field group is completed. Returns <code>true</code> to
     *         override the default process.
     */
    protected boolean overrideFieldGroup(IHtmlViewContext ctx, IHtmlOut out, IUiRef<?> instanceRef, PropertyGroup group)
            throws ViewBuilderException, IOException {
        return false;
    }

    protected List<IFormProperty> overrideFieldSelection(IUiRef<?> instanceRef, PropertyGroup group,
            List<IFormProperty> selection)
            throws ViewBuilderException, IOException {
        return selection;
    }

    protected abstract IHtmlOut beginCategory(IHtmlViewContext ctx, IHtmlOut out, PropertyCategory category)
            throws ViewBuilderException, IOException;

    protected abstract IHtmlOut beginField(IHtmlViewContext ctx, IHtmlOut out, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException;

    protected abstract void fieldBody(IHtmlViewContext ctx, IHtmlOut out, IUiRef<?> instanceRef, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException;

    protected abstract void endField(IHtmlViewContext ctx, IHtmlOut out, IHtmlOut fieldOut, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException;

    protected abstract void endCategory(IHtmlViewContext ctx, IHtmlOut out, IHtmlOut catOut, PropertyCategory category);

    protected void endForm(IHtmlViewContext ctx, IHtmlOut out, IUiRef<?> ref)
            throws ViewBuilderException, IOException {
    }

    protected IHtmlOut afterForm(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return out;
    }

    protected IHtmlOut extras(IHtmlViewContext ctx, IHtmlOut out, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
        return out;
    }

}
