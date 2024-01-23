package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.lily.app.IDataApplication;

public interface IDataAppHost
        extends
            IVirtualHost {

    IDataApplication getDataApp();

    DataContext getDataContext();

    @Override
    DataAppSite getRoot();

}
