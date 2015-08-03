package net.bodz.bas.html.viz.builtin;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlDivTag;
import net.bodz.bas.html.dom.tag.HtmlDlTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.dom.tag.HtmlTableTag;
import net.bodz.bas.html.dom.tag.HtmlTrTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.potato.ref.UiPropertyRef;
import net.bodz.bas.repr.form.IFieldDecl;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;

public class Map_htm
        extends AbstractFormInput_htm<Map<?, ?>> {

    public Map_htm() {
        super(Map.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, UiPropertyRef<Map<?, ?>> ref,
            IFieldDecl fieldDecl, IOptions options)
            throws ViewBuilderException, IOException {
        Map<?, ?> map = ref.get();
        if (map == null) {
            out.span().text("n/a");
            return out;
        }

        String face = fieldDecl.getFace();
        if (face == null)
            face = "table";

        switch (face) {
        case "table":
            HtmlTableTag table = out.table().border("0");
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                HtmlTrTag tr = table.tr();
                tr.td().text(key);
                tr.td().text(value);
            }
            break;

        case "div":
            HtmlDivTag div = out.div();
            for (Entry<?, ?> entry : map.entrySet()) {
                div.span().text(entry.getValue());
            }
            break;

        case "ul":
        case "ol":
            IHtmlTag xl = "ul".equals(face) ? out.ul() : out.ol();
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                xl.li().text(key + ": " + value);
            }
            break;

        case "dl":
            HtmlDlTag dl = out.dl();
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                dl.dt().text(key);
                dl.dd().text(value);
            }
            break;

        case "select":
            HtmlSelectTag select = out.select();
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                select.option().value(key.toString()).text(value);
            }
            break;

        default:
            throw new ViewBuilderException("Unsupported map face: " + fieldDecl.getFace());
        }

        return out;
    }
}
