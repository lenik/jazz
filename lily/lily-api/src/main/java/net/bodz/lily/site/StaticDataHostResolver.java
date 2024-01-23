package net.bodz.lily.site;

import net.bodz.bas.c.autowire.ProjectList;
import net.bodz.bas.site.StaticVhostResolver;
import net.bodz.lily.app.DataApplication;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;

public abstract class StaticDataHostResolver
        extends StaticVhostResolver {

    @Override
    protected DataAppHost buildVirtualHost() {
        DataApplication dataApp = DataApps.getPreferred();

        DataAppSite root = buildRoot(dataApp);

        DataAppHost vhost = new DataAppHost(dataApp, root);
        String projectName = ProjectList.INSTANCE.topLevelName();
        vhost.setName(projectName);

        return vhost;
    }

    protected abstract DataAppSite buildRoot(IDataApplication dataApp);

}
