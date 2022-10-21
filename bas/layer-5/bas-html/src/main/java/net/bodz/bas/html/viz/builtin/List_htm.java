package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlDiv;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class List_htm
        extends AbstractFormInput_htm<List<?>> {

    public List_htm() {
        super(List.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<List<?>> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        List<?> list = ref.get();
        if (list == null) {
            out.span().text("n/a");
            return;
        }

        String face = fieldDecl.getFace();
        if (face == null)
            face = "ul";

        switch (face) {
        case "div":
            HtmlDiv div = out.div();
            for (Object item : list) {
                div.span().text(item);
            }
            break;

        case "ul":
        case "ol":
            IHtmlOut xl = "ul".equals(face) ? out.ul() : out.ol();
            for (Object item : list) {
                xl.li().text(item);
            }
            break;

        case "select":
            HtmlSelect select = out.select();
            int n = list.size();
            for (int i = 0; i < n; i++) {
                Object item = list.get(i);
                select.option().value("" + i).text(item);
            }
            break;

        default:
            throw new ViewBuilderException("Unsupported list face: " + fieldDecl.getFace());
        }
    }

}
