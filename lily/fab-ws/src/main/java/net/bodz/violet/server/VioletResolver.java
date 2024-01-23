package net.bodz.violet.server;

import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.site.DataAppSite;
import net.bodz.lily.site.StaticDataHostResolver;

public class VioletResolver
        extends StaticDataHostResolver {

    @Override
    protected DataAppSite buildRoot(IDataApplication dataApp) {
        return new VioletSite(dataApp);
    }

}
