package net.bodz.lily.model.base.security.impl;

import java.io.IOException;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHttpViewBuilder;
import net.bodz.bas.html.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.ui.dom1.IUiRef;

import net.bodz.lily.model.base.security.User;

public class User_htm
        extends AbstractHttpViewBuilder<User>
        implements IBasicSiteAnchors {
    
    public User_htm() {
        super(User.class);
    }

    @Override
    public IHtmlTag buildHtmlView(IHttpViewContext ctx, IHtmlTag out, IUiRef<User> ref, IOptions options)
            throws ViewBuilderException, IOException {
        return out;
    }

}
