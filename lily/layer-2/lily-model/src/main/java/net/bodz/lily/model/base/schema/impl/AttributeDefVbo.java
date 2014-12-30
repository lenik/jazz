package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.AttributeDef;

public class AttributeDefVbo
        extends AbstractDefinitionVbo<AttributeDef> {

    public AttributeDefVbo() {
        super(AttributeDef.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IHtmlTag out, IUiRef<AttributeDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return ctx;
    }

}
