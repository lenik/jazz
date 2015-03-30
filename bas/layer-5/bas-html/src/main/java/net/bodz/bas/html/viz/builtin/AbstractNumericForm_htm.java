package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public abstract class AbstractNumericForm_htm<T extends Number>
        extends AbstractTextForm_htm<T> {

    public AbstractNumericForm_htm(Class<T> valueClass) {
        super(valueClass);
    }

    @Override
    protected HtmlInputTag createScreenInput(IHtmlTag out, UiPropertyRef<T> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {
        HtmlInputTag input = out.input().type("number").class_("noprint");
        return input;
    }

}
