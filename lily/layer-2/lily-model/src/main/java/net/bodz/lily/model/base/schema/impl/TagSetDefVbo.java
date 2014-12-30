package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.TagSetDef;

public class TagSetDefVbo
        extends AbstractDefinitionVbo<TagSetDef> {

    public TagSetDefVbo() {
        super(TagSetDef.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<TagSetDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return ctx;
    }

}
