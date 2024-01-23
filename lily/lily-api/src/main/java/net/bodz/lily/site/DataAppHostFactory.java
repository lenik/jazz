package net.bodz.lily.site;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHostFactory;
import net.bodz.lily.app.IDataApplication;

public class DataAppHostFactory
        implements
            IVirtualHostFactory {

    ConnectOptions connectOptionsTemplate;

    public DataAppHostFactory(ConnectOptions connectOptionsTemplate) {
        this.connectOptionsTemplate = connectOptionsTemplate;
    }

    @Override
    public IVirtualHost buildVirtualHost(String name, String databaseName) {
        IDataApplication dataApp = null;
        DataAppSite site = null;
        DataAppHost vhost = new DataAppHost(dataApp, site);
        vhost.setName(name);
        // vhost.addBinding(name + ".xxx");

        ConnectOptions options = connectOptionsTemplate.clone();
        options.setDatabase(databaseName);

//        vhost.setAttribute(ConnectOptions.ATTRIBUTE_KEY, options);
        return vhost;
    }

}
