package net.bodz.bas.html.viz.builtin;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlInputTag;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;

public class String_htm
        extends AbstractTextForm_htm<String> {

    public String_htm() {
        super(String.class);
    }

    @Override
    protected HtmlInputTag createScreenInput(IHtmlTag out, UiPropertyRef<String> ref, IFieldDecl fieldDecl) {
        HtmlInputTag input = out.input();

        char echoChar = fieldDecl.getEchoChar();
        if (echoChar == '\0')
            input.type("text").class_("noprint");
        else
            input.type("password").class_("noprint");

        return input;
    }

}
