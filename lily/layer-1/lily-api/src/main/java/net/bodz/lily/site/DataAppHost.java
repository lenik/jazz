package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.bas.site.vhost.MutableVirtualHost;
import net.bodz.lily.app.IDataApplication;

public class DataAppHost
        extends MutableVirtualHost
        implements
            IDataAppHost {

    private static final long serialVersionUID = 1L;

    IDataApplication dataApp;
    DataContext dataContext;

    public DataAppHost(IDataApplication dataApp, DataAppSite root) {
        super(root);
        if (dataApp == null)
            throw new NullPointerException("dataApp");
        this.dataApp = dataApp;
    }

    @Override
    public IDataApplication getDataApp() {
        return dataApp;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public DataAppSite getRoot() {
        return (DataAppSite) super.getRoot();
    }

    @Override
    public void setRoot(ISiteRoot root) {
        if (!(root instanceof DataAppSite))
            throw new IllegalArgumentException("Not a " + DataAppSite.class.getName());
        super.setRoot(root);
    }

}
