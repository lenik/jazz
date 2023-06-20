package net.bodz.lily.security.login;

import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;

public class VhostLoginManagerProvider
        implements
            ILoginManagerProvider {

    @Override
    public synchronized ILoginManager getLoginManager() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHostOpt();
        if (vhost == null)
//            return null;
            throw new NullPointerException("vhost");

        IDataApplication app = DataApps.fromRequest();
        if (app == null)
            throw new NullPointerException("app");

        ILoginManager manager = vhost.getAttribute(ILoginManager.ATTRIBUTE_NAME);
        if (manager == null) {
            manager = new LoginManager(app.getDataContext());
            vhost.setAttribute(ILoginManager.ATTRIBUTE_NAME, manager);
        }
        return manager;
    }

}
