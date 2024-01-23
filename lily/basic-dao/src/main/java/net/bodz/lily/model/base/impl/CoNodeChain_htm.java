package net.bodz.lily.model.base.impl;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.lily.model.base.CoNode;
import net.bodz.lily.model.base.CoNodeChain;

public class CoNodeChain_htm<T extends CoNode<T, ?>>
        extends AbstractHtmlViewBuilder<CoNodeChain<T>> {

    public CoNodeChain_htm() {
        super(CoNodeChain.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<CoNodeChain<T>> ref)
            throws ViewBuilderException, IOException {
        CoNodeChain<T> chain = ref.get();
        HtmlUl ul = out.ul().class_("node-chain");
        for (T node : chain) {
            ul.li().text(node.getNodeLabel());
        }
        return out;
    }

}
