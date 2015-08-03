package net.bodz.bas.html.viz.util;

import java.io.IOException;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlTableTag;
import net.bodz.bas.html.dom.tag.HtmlTdTag;
import net.bodz.bas.html.dom.tag.HtmlTrTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldCategory;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
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
    protected void nullInstance(IHtmlTag out, IUiRef<T> ref) {
        out.text("just null.");
    }

    @Override
    protected IHtmlTag beginForm(IHtmlViewContext ctx, IHtmlTag out, IUiRef<?> ref, IOptions options)
            throws ViewBuilderException, IOException {
        if (embedded)
            return out;
        else
            return out.form();
    }

    @Override
    protected IHtmlTag beginCategory(IHtmlViewContext ctx, IHtmlTag out, FieldCategory category)
            throws ViewBuilderException {
        HtmlTableTag table = out.table().border("0").class_("field-category");
        return table;
    }

    @Override
    protected void endCategory(IHtmlViewContext ctx, IHtmlTag out, IHtmlTag catOut, FieldCategory category) {
    }

    @Override
    protected IHtmlTag beginField(IHtmlViewContext ctx, IHtmlTag table, IFieldDecl fieldDecl)
            throws ViewBuilderException {
        HtmlTrTag tr = table.tr().class_("field-row");
        tr.td().class_("field-label").text(fieldDecl.getLabel());
        return tr;
    }

    @Override
    protected void fieldBody(IHtmlViewContext ctx, IHtmlTag tr, IUiRef<?> instanceRef, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {
        HtmlTdTag bodyTd = tr.td().class_("field-body");

        IProperty property = fieldDecl.getProperty();
        UiPropertyRef<Object> propertyRef = new UiPropertyRef<Object>(instanceRef, property);

        IHttpViewBuilderFactory factory = ctx.query(IHttpViewBuilderFactory.class);
        if (factory == null)
            throw new IllegalConfigException(IHttpViewBuilderFactory.class + " isn't set.");
        else
            factory.buildView(ctx, bodyTd, propertyRef);
    }

    @Override
    protected void endField(IHtmlViewContext ctx, IHtmlTag out, IHtmlTag fieldOut, IFieldDecl fieldDecl) {
    }

}
