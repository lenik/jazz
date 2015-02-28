package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.PriorityDef;

public class PriorityDef_htm
        extends AbstractDefinition_htm<PriorityDef> {

    public PriorityDef_htm() {
        super(PriorityDef.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<PriorityDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

}
