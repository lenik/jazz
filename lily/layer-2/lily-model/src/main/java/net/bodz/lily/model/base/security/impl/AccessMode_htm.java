package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlOptionTag;
import net.bodz.bas.html.dom.tag.HtmlSelectTag;
import net.bodz.bas.html.viz.AbstractHttpViewBuilder;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

@ExcludedFromIndex
public class AccessMode_htm
        extends AbstractHttpViewBuilder<Integer> {

    public AccessMode_htm() {
        super(Integer.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<Integer> ref, IOptions options)
            throws ViewBuilderException, IOException {
        int val = ref.get();

        HtmlSelectTag select = out.select();
        for (AccessMode mode : AccessMode.METADATA.getValues()) {
            int key = mode.getKey();
            HtmlOptionTag option = select.option().value(key).text(mode.getLabel());
            if (val == key)
                option.selected("selected");
        }

        return select;
    }

}
