package net.bodz.lily.model.base.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlOption;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.ui.dom1.IUiRef;

@Face("priority")
public class Priority_htm
        extends AbstractHtmlViewBuilder<Integer> {

    public Priority_htm() {
        super(Integer.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<Integer> ref)
            throws ViewBuilderException, IOException {
        // ctx.query(PriorityMapper.class);
        List<Pair<Integer, String>> predefs = new ArrayList<>();
        predefs.add(Pair.of(-10, "紧急"));
        predefs.add(Pair.of(-5, "高"));
        predefs.add(Pair.of(0, "中等"));
        predefs.add(Pair.of(5, "普通"));
        predefs.add(Pair.of(10, "低"));

        HtmlSelect select = out.select();
        int priority = ref.get();
        HtmlOption nearest = null;
        int nearestN = 0;

        for (Pair<Integer, String> predef : predefs) {
            Integer n = predef.getKey();
            HtmlOption option = select.option().value(n).text(predef.getValue());
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
