package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.io.tag.HtmlTextarea;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;

public abstract class AbstractTextForm_htm<T>
        extends AbstractFormInput_htm<T> {

    static final Logger logger = LoggerFactory.getLogger(AbstractTextForm_htm.class);

    public AbstractTextForm_htm(Class<T> valueClass) {
        super(valueClass);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<T> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        T value = null;
        try {
            value = ref.get();
        } catch (Exception e) {
            out.div().class_("err").text(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        IFormatter<T> formatter = Typers.getTyper(ref.getValueType(), IFormatter.class);
        if (formatter == null)
            throw new IllegalUsageException("No formatter available for " + ref.getValueType());

        String str = null;
        if (value != null)
            try {
                str = formatter.format(value/* ,options */);
            } catch (FormatException e) {
                throw new ViewBuilderException(e.getMessage(), e);
            }

        IHtmlOut tag = createScreenInput(out, ref, fieldDecl);
        if (tag != null) {
            if (tag instanceof HtmlInput)
                FieldDeclToHtml.apply((HtmlInput) tag, fieldDecl);
            else if (tag instanceof HtmlTextarea)
                FieldDeclToHtml.apply((HtmlTextarea) tag, fieldDecl);
            if (str != null)
                tag.attr("value", str);
        }

        out.span().class_("print").text(str);
    }

    protected abstract IHtmlOut createScreenInput(IHtmlOut out, UiPropertyRef<T> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException;

}
