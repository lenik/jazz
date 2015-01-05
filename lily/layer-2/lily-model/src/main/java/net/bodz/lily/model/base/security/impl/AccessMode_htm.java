package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.html.viz.builtin.Integer_htm;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class AccessMode_htm
        extends AbstractHtmlViewBuilder<Integer> {

    public AccessMode_htm() {
        super(Integer.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<Integer> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return new Integer_htm().buildHtmlView(ctx, out, ref, options);
    }

}
