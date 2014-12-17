package net.bodz.lily.model.base.schema.impl;

import java.io.IOException;

import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.schema.PhaseDef;

public class PhaseDefVbo
        extends AbstractHtmlViewBuilder<PhaseDef>
        implements IBasicSiteAnchors {
    
    public PhaseDefVbo() {
        super(PhaseDef.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<PhaseDef> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return ctx;
    }

}
