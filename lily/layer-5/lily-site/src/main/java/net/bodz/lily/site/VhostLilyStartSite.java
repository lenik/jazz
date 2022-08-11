package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostScope;

@VirtualHostScope
public abstract class VhostLilyStartSite
        extends LilyStartSite {

    static final Logger logger = LoggerFactory.getLogger(VhostLilyStartSite.class);

    IVirtualHost virtualHost;

    public VhostLilyStartSite(IVirtualHost virtualHost) {
        super(createDataContext(virtualHost));
        this.virtualHost = virtualHost;
        virtualHost.setAttribute(ISiteRoot.ATTRIBUTE_NAME, this);
    }

    static DataContext createDataContext(IVirtualHost vhost) {
        ConnectOptions connectOptions = vhost.getAttribute(ConnectOptions.ATTRIBUTE_KEY);
        DataContext dataContext = new DataContext(connectOptions);
        return dataContext;
    }

}
