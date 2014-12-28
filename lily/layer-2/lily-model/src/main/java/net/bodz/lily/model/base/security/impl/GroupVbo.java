package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.security.Group;

public class GroupVbo
        extends AbstractHtmlViewBuilder<Group>
        implements IBasicSiteAnchors {
    
    public GroupVbo() {
        super(Group.class);
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<Group> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return ctx;
    }

}
