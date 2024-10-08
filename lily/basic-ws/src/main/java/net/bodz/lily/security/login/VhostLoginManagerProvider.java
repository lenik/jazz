package net.bodz.lily.security.login;

import net.bodz.bas.site.ISiteRoot;
import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.security.auth.ILoginManagerProvider;

public class VhostLoginManagerProvider
        implements
            ILoginManagerProvider {

    @Override
    public synchronized ILoginManager getLoginManager() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();

        ISiteRoot root = vhost.getRoot();

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
