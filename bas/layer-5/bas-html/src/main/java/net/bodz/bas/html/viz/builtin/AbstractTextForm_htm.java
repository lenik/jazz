package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.html.dom.tag.HtmlTextareaTag;
import net.bodz.bas.html.util.FieldHtmlUtil;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.FieldDeclBuilder;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;

public abstract class AbstractTextForm_htm<T>
        extends AbstractFormInput_htm<T> {

    static FieldDeclBuilder fieldDeclBuilder = new FieldDeclBuilder();

    public AbstractTextForm_htm(Class<T> valueClass) {
        super(valueClass);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, UiPropertyRef<T> ref, IFieldDecl fieldDecl,
            IOptions options)
            throws ViewBuilderException, IOException {
        T value = ref.get();

        IFormatter<T> formatter = Typers.getTyper(ref.getValueType(), IFormatter.class);
        if (formatter == null)
            throw new IllegalUsageException("No formatter available for " + ref.getValueType());

        String str = null;
        if (value != null)
            str = formatter.format(value/* ,options */);

        IHtmlTag tag = createScreenInput(out, ref, fieldDecl);
        if (tag != null) {
            if (tag instanceof HtmlInputTag)
                FieldHtmlUtil.apply((HtmlInputTag) tag, fieldDecl, options);
            else if (tag instanceof HtmlTextareaTag)
                FieldHtmlUtil.apply((HtmlTextareaTag) tag, fieldDecl, options);
            if (str != null)
                tag.attr("value", str);
        }

        out.span().class_("print").text(str);
        return out;
    }

    protected abstract IHtmlTag createScreenInput(IHtmlTag out, UiPropertyRef<T> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException;

}
