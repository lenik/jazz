package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.TagSetDef;

public class TagSetDef_htm
        extends AbstractDefinition_htm<TagSetDef> {

    public TagSetDef_htm() {
        super(TagSetDef.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<TagSetDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

}
