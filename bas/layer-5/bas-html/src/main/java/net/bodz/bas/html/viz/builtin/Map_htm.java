package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlDiv;
import net.bodz.bas.html.io.tag.HtmlDl;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.io.tag.HtmlTable;
import net.bodz.bas.html.io.tag.HtmlTr;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFormProperty;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class Map_htm
        extends AbstractFormInput_htm<Map<?, ?>> {

    public Map_htm() {
        super(Map.class);
    }

    @Override
    public void buildHtmlView(IHtmlViewContext ctx, IHtmlOut out, UiPropertyRef<Map<?, ?>> ref, IFormProperty fieldDecl)
            throws ViewBuilderException, IOException {
        Map<?, ?> map = ref.get();
        if (map == null) {
            out.span().text("n/a");
            return;
        }

        String face = fieldDecl.getFace();
        if (face == null)
            face = "table";

        switch (face) {
        case "table":
            HtmlTable table = out.table().border("0");
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                HtmlTr tr = table.tr();
                tr.td().text(key);
                tr.td().text(value);
            }
            break;

        case "div":
            HtmlDiv div = out.div();
            for (Entry<?, ?> entry : map.entrySet()) {
                div.span().text(entry.getValue());
            }
            break;

        case "ul":
        case "ol":
            IHtmlOut xl = "ul".equals(face) ? out.ul() : out.ol();
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                xl.li().text(key + ": " + value);
            }
            break;

        case "dl":
            HtmlDl dl = out.dl();
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                dl.dt().text(key);
                dl.dd().text(value);
            }
            break;

        case "select":
            HtmlSelect select = out.select();
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                select.option().value(key.toString()).text(value);
            }
            break;

        default:
            throw new ViewBuilderException("Unsupported map face: " + fieldDecl.getFace());
        }
    }

}
