package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.CategoryDef;

public class CategoryDefVbo
        extends AbstractDefinitionVbo<CategoryDef> {

    public CategoryDefVbo() {
        super(CategoryDef.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<CategoryDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return ctx;
    }

}
