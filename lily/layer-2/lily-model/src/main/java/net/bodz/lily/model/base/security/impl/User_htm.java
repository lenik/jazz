package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.lily.model.base.security.User;

public class User_htm
        extends AbstractHtmlViewBuilder<User>
        implements IBasicSiteAnchors {
    
    public User_htm() {
        super(User.class);
    }

    @Override
    public void buildHtmlViewStart(IHtmlViewContext ctx, IHtmlTag out, IUiRef<User> ref)
            throws ViewBuilderException, IOException {
    }

}
