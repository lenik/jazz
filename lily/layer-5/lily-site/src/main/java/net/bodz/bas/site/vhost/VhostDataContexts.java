package net.bodz.bas.site.vhost;

import net.bodz.bas.db.ctx.AbstractDataContexts;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.jdbc.ConnectOptions;

public class VhostDataContexts
        extends AbstractDataContexts<IVirtualHost> {

    @Override
    public ConnectOptions getConnectOptions(IVirtualHost key) {
        if (key instanceof IVirtualHost) {
            IVirtualHost vhost = (IVirtualHost) key;
            ConnectOptions opts = vhost.getAttribute(ConnectOptions.ATTRIBUTE_KEY);
            return opts;
        }
        return null;
    }

    public DataContext forCurrentRequest() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();
        return get(vhost);
    }

    private static VhostDataContexts instance = new VhostDataContexts();

    public static VhostDataContexts getInstance() {
        return instance;
    }

}
