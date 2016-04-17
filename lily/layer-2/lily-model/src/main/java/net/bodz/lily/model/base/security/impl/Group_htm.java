package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.lily.model.base.security.Group;

public class Group_htm
        extends AbstractHtmlViewBuilder<Group>
        implements IBasicSiteAnchors {

    public Group_htm() {
        super(Group.class);
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<Group> ref)
            throws ViewBuilderException, IOException {
        return out;
    }

}
