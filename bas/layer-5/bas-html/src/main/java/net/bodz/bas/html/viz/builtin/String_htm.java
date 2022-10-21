package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;

public class String_htm
        extends AbstractTextForm_htm<String> {

    public String_htm() {
        super(String.class);
    }

    @Override
    protected HtmlInput createScreenInput(IHtmlOut out, UiPropertyRef<String> ref, IFormProperty fieldDecl) {
        HtmlInput input = out.input();

        char echoChar = fieldDecl.getEchoChar();
        if (echoChar == '\0')
            input.type("text").class_("noprint");
        else
            input.type("password").class_("noprint");

        return input;
    }

}
