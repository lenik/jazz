package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.lily.model.base.schema.ParameterDef;

public class ParameterDef_htm
        extends AbstractDefinition_htm<ParameterDef> {

    public ParameterDef_htm() {
        super(ParameterDef.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<ParameterDef> ref)
            throws ViewBuilderException, IOException {
        return out;
    }

}
