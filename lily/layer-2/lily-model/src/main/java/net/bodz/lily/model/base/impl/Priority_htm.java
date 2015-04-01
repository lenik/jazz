package net.bodz.lily.model.base.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlOptionTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.viz.AbstractHttpViewBuilder;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.ui.dom1.IUiRef;

public class Priority_htm
        extends AbstractHttpViewBuilder<Integer> {

    public Priority_htm() {
        super(Integer.class, "Priority");
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<Integer> ref, IOptions options)
            throws ViewBuilderException, IOException {
        // ctx.query(PriorityMapper.class);
        List<Pair<Integer, String>> predefs = new ArrayList<>();
        predefs.add(Pair.of(-10, "紧急"));
        predefs.add(Pair.of(-5, "高"));
        predefs.add(Pair.of(0, "中等"));
        predefs.add(Pair.of(5, "普通"));
        predefs.add(Pair.of(10, "低"));

        HtmlSelectTag select = out.select();
        int priority = ref.get();
        HtmlOptionTag nearest = null;
        int nearestN = 0;

        for (Pair<Integer, String> predef : predefs) {
            Integer n = predef.getKey();
            HtmlOptionTag option = select.option().value(n).text(predef.getValue());
            if (nearest == null || (n <= priority && n > nearestN)) {
                nearest = option;
                nearestN = n;
            }
        }
        if (nearest != null)
            nearest.selected("selected");

        // FieldHtmlUtil.apply(input, fieldDecl, options);
        return out;
    }

}
