package net.bodz.lily.site;

import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VhostDataContexts;

public abstract class VhostLilyStartSite
        extends LilyStartSite {

    IVirtualHost vhost;

    public VhostLilyStartSite(IVirtualHost vhost) {
        super(VhostDataContexts.getInstance().get(vhost));
        this.vhost = vhost;
    }

}
