package net.bodz.bas.html.viz.builtin;

import java.math.BigDecimal;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;

public class BigDecimal_htm
        extends AbstractNumericForm_htm<BigDecimal> {

    public BigDecimal_htm() {
        super(BigDecimal.class);
    }

    @Override
    protected HtmlInput createScreenInput(IHtmlOut out, UiPropertyRef<BigDecimal> ref, IFieldDecl fieldDecl) {
        HtmlInput input = out.input().type("number").class_("noprint");
        return input;
    }

}
