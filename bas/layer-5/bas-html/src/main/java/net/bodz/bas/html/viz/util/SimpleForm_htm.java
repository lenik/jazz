package net.bodz.bas.html.viz.util;

import java.io.IOException;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlTableTag;
import net.bodz.bas.html.dom.tag.HtmlTrTag;
import net.bodz.bas.html.viz.IHtmlViewBuilderFactory;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldCategory;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class SimpleForm_htm
        extends AbstractForm_htm<Object> {

    public SimpleForm_htm() {
        super(Object.class);
    }

    @Override
    protected void nullInstance(IHtmlTag out, IUiRef<Object> ref) {
        out.text("just null.");
    }

    @Override
    protected IHtmlTag beginCategory(IHtmlViewContext ctx, IHtmlTag out, FieldCategory category)
            throws ViewBuilderException {
        HtmlTableTag table = out.table().border("1");
        return table;
    }

    @Override
    protected void endCategory(IHtmlViewContext ctx, IHtmlTag out, IHtmlTag catOut, FieldCategory category) {
    }

    @Override
    protected IHtmlTag beginField(IHtmlViewContext ctx, IHtmlTag out, IFieldDecl fieldDecl)
            throws ViewBuilderException {
        HtmlTrTag tr = out.tr();
        tr.td().text(fieldDecl.getLabel());
        return tr;
    }

    @Override
    protected void fieldBody(IHtmlViewContext ctx, IHtmlTag out, IUiRef<Object> instanceRef, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {
        IProperty property = fieldDecl.getProperty();
        UiPropertyRef<Object> propertyRef = new UiPropertyRef<Object>(instanceRef, property);

        IHtmlViewBuilderFactory factory = ctx.query(IHtmlViewBuilderFactory.class);
        if (factory == null)
            throw new IllegalConfigException(IHtmlViewBuilderFactory.class + " isn't set.");
        else
            factory.buildView(ctx, out, propertyRef);
    }

    @Override
    protected void endField(IHtmlViewContext ctx, IHtmlTag out, IHtmlTag fieldOut, IFieldDecl fieldDecl) {
    }

}
