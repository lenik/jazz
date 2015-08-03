package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlOptionTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class AccessMode_htm
        extends AbstractHtmlViewBuilder<Integer> {

    public AccessMode_htm() {
        super(Integer.class, "AccessMode");
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<Integer> ref, IOptions options)
            throws ViewBuilderException, IOException {
        Integer val = ref.get();
        if (val == null)
            throw new NullPointerException("val");
        int n = val;

        HtmlSelectTag select = out.select();
        for (AccessMode mode : AccessMode.METADATA.getValues()) {
            int key = mode.getKey();
            HtmlOptionTag option = select.option().value(key).text(mode.getLabel());
            if (n == key)
                option.selected("selected");
        }

        return select;
    }

}
