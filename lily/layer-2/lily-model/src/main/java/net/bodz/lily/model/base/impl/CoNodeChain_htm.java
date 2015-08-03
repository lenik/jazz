package net.bodz.lily.model.base.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.HtmlUlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.CoNode;
import net.bodz.lily.model.base.CoNodeChain;

public class CoNodeChain_htm<T extends CoNode<T, ?>>
        extends AbstractHtmlViewBuilder<CoNodeChain<T>> {

    public CoNodeChain_htm() {
        super(CoNodeChain.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<CoNodeChain<T>> ref, IOptions options)
            throws ViewBuilderException, IOException {
        CoNodeChain<T> chain = ref.get();
        HtmlUlTag ul = out.ul().class_("node-chain");
        for (T node : chain) {
            ul.li().text(node.getNodeLabel());
        }
        return out;
    }

}
