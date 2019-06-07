package net.bodz.lily.security.impl;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlOption;
import net.bodz.bas.html.io.tag.HtmlSelect;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;

@Face("AccessMode")
public class AccessMode_htm
        extends AbstractHtmlViewBuilder<Integer> {

    public AccessMode_htm() {
        super(Integer.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<Integer> ref)
            throws ViewBuilderException, IOException {
        Integer val = ref.get();
        if (val == null)
            throw new NullPointerException("val");
        int n = val;

        HtmlSelect select = out.select();
        for (AccessMode mode : AccessMode.meta.getValues()) {
            int key = mode.getKey();
            HtmlOption option = select.option().value(key);
            if (n == key)
                option.selected("selected");
            option.text(mode.getLabel());
        }
        return out;
    }

}
