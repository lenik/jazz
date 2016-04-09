package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.lily.model.base.schema.TagGroupDef;

public class TagGroupDef_htm
        extends AbstractDefinition_htm<TagGroupDef> {

    public TagGroupDef_htm() {
        super(TagGroupDef.class);
    }

    @Override
    public void buildHtmlViewStart(IHtmlViewContext ctx, IHtmlTag out, IUiRef<TagGroupDef> ref)
            throws ViewBuilderException, IOException {
    }

}
