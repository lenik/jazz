package net.bodz.bas.html.viz.util;

import java.io.IOException;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlTable;
import net.bodz.bas.html.io.tag.HtmlTd;
import net.bodz.bas.html.io.tag.HtmlTr;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.PropertyCategory;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.IHttpViewBuilderFactory;
import net.bodz.bas.ui.dom1.IUiRef;

public class DefaultForm_htm<T>
        extends AbstractForm_htm<T> {

    private boolean embedded = false;

    public DefaultForm_htm() {
        super(Object.class);
    }

    public DefaultForm_htm(boolean embedded) {
        this();
        this.embedded = embedded;
    }

    @Override
    protected void nullInstance(IHtmlOut out, IUiRef<T> ref) {
        out.text("just null.");
    }

    @Override
    protected IHtmlOut beginForm(IHtmlViewContext ctx, IHtmlOut out, IUiRef<?> ref)
            throws ViewBuilderException, IOException {
        if (embedded)
            return out;
        else
            return out.form();
    }

    @Override
    protected IHtmlOut beginCategory(IHtmlViewContext ctx, IHtmlOut out, PropertyCategory category)
            throws ViewBuilderException {
        HtmlTable table = out.table().border("0").class_("field-category");
        return table;
    }

    @Override
    protected void endCategory(IHtmlViewContext ctx, IHtmlOut out, IHtmlOut catOut, PropertyCategory category) {
    }

    @Override
    protected IHtmlOut beginField(IHtmlViewContext ctx, IHtmlOut table, IFormProperty fieldDecl)
            throws ViewBuilderException {
        HtmlTr tr = table.tr().class_("field-row");
        tr.td().class_("field-label").text(fieldDecl.getLabel());
        return tr;
    }

    @Override
    protected void fieldBody(IHtmlViewContext ctx, IHtmlOut tr, IUiRef<?> instanceRef, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        HtmlTd bodyTd = tr.td().class_("field-body");

        IProperty property = fieldDecl.getProperty();
        UiPropertyRef<Object> propertyRef = new UiPropertyRef<Object>(instanceRef, property);

        IHttpViewBuilderFactory factory = ctx.query(IHttpViewBuilderFactory.class);
        if (factory == null)
            throw new IllegalConfigException(IHttpViewBuilderFactory.class + " isn't set.");
        else
            factory.buildView(ctx, bodyTd, propertyRef);
    }

    @Override
    protected void endField(IHtmlViewContext ctx, IHtmlOut out, IHtmlOut fieldOut, IFormProperty fieldDecl) {
    }

}
