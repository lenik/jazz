package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.TagDef;

public class TagDef_htm
        extends AbstractDefinition_htm<TagDef> {

    public TagDef_htm() {
        super(TagDef.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<TagDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

}
