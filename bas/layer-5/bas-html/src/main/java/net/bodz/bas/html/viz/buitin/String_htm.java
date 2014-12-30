package net.bodz.bas.html.viz.buitin;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

public class String_htm
        extends AbstractHtmlViewBuilder<String> {

    public String_htm() {
        super(String.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IHtmlTag parent, IUiRef<String> ref, IOptions options)
            throws ViewBuilderException, IOException {

        return ctx;
    }

}
