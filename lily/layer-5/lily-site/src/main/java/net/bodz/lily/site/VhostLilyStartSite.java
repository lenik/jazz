package net.bodz.lily.site;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostScope;
import net.bodz.lily.app.DataApps;

@VirtualHostScope
public abstract class VhostLilyStartSite
        extends LilyStartSite {

    static final Logger logger = LoggerFactory.getLogger(VhostLilyStartSite.class);

    IVirtualHost virtualHost;

    public VhostLilyStartSite(IVirtualHost virtualHost) {
        super(DataApps.lazyCreate(virtualHost));
        this.virtualHost = virtualHost;
        virtualHost.setAttribute(ISiteRoot.ATTRIBUTE_NAME, this);
    }

}
