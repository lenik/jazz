package net.bodz.bas.html.viz.builtin;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlInput;
import net.bodz.bas.html.util.FieldDeclToHtml;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class State_htm
        extends AbstractFormInput_htm<State> {

    public State_htm() {
        super(State.class); // TODO , "select");
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<State> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {
        State state = ref.get();

        HtmlInput input = out.input();
        FieldDeclToHtml.apply(input, fieldDecl);

        // input.title(state.getName());
        input.type("text");
        input.value(state.getName());
    }

}
