package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public abstract class AbstractNumericForm_htm<T extends Number>
        extends AbstractTextForm_htm<T> {

    public AbstractNumericForm_htm(Class<T> valueClass) {
        super(valueClass);
    }

    @Override
    protected HtmlInput createScreenInput(IHtmlOut out, UiPropertyRef<T> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        HtmlInput input = out.input().type("number").class_("noprint");
        return input;
    }

}
