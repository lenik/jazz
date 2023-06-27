package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.site.BasicSite;
import net.bodz.lily.app.IDataApplication;

public abstract class DataAppSite
        extends BasicSite {

    protected final IDataApplication app;
    protected final DataContext dataContext;

    public DataAppSite(IDataApplication app) {
        this.app = app;
        this.dataContext = app.getDataContext();
    }

    public IDataApplication getDataApp() {
        return app;
    }

    public DataContext getDataContext() {
        return dataContext;
    }

}
