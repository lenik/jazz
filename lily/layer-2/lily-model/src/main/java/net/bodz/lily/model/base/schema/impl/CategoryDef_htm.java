package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.lily.model.base.schema.CategoryDef;

public class CategoryDef_htm
        extends AbstractDefinition_htm<CategoryDef> {

    public CategoryDef_htm() {
        super(CategoryDef.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<CategoryDef> ref)
            throws ViewBuilderException, IOException {
        return out;
    }

}
