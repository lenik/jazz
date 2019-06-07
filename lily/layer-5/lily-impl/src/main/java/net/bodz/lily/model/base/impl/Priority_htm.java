package net.bodz.lily.model.base.impl;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlOption;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.viz.ViewBuilderException;
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
        if (ref == null)
            throw new NullPointerException("ref");

        // ctx.query(PriorityMapper.class);
        TreeMap<Integer, String> predefs = new TreeMap<>();
        predefs.put(-10, "紧急");
        predefs.put(-5, "高");
        predefs.put(0, "中等");
        predefs.put(5, "普通");
        predefs.put(10, "低");

        HtmlSelect select = out.select();
        // FieldHtmlUtil.apply(select, fieldDecl, options);

        Integer priority = ref.get();
        Integer floor = priority == null ? null : predefs.floorKey(priority);
        int selectedKey = floor == null ? -10 : floor;

        for (Entry<Integer, String> predef : predefs.entrySet()) {
            int key = predef.getKey();
            HtmlOption option = select.option().value(key);
            if (key == selectedKey)
                option.selected("selected");
            option.text(predef.getValue());
        }

        return out;
    }

}
