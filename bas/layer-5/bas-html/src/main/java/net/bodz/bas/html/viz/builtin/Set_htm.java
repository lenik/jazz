package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Set;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlDivTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Set_htm
        extends AbstractFormInput_htm<Set<?>> {

    public Set_htm() {
        super(Set.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Set<?>> ref, IFieldDecl fieldDecl)
            throws ViewBuilderException, IOException {
        Set<?> set = ref.get();
        if (set == null) {
            out.span().text("n/a");
            return;
        }

        String face = fieldDecl.getFace();
        if (face == null)
            face = "ul";

        switch (face) {
        case "div":
            HtmlDivTag div = out.div();
            for (Object item : set) {
                div.span().text(item);
            }
            break;

        case "ul":
        case "ol":
            IHtmlTag xl = "ul".equals(face) ? out.ul() : out.ol();
            for (Object item : set) {
                xl.li().text(item);
            }
            break;

        default:
            throw new ViewBuilderException("Unsupported set face: " + fieldDecl.getFace());
        }
    }

}
