package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.man.SysManager;
import net.bodz.bas.site.BasicSite;
import net.bodz.bas.site.org.ICrawler;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.DataApplication;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.security.login.LoginManagerWs;
import net.bodz.lily.tool.wsdoc.WsDocSite;

public abstract class LilyStartSite
        extends BasicSite {

    static final Logger logger = LoggerFactory.getLogger(LilyStartSite.class);

    public static final String PATH_SYSMAN = "sysmgr";

    protected final IDataApplication app;
    protected final DataContext dataContext;
//    protected ILoginManager loginManager;

    public LilyStartSite() {
        this(DataApps.getPreferred());
    }

    public LilyStartSite(IDataApplication app) {
        this.app = app;

        if (app instanceof DataApplication) {
            DataApplication mutable = (DataApplication) app;
        }

        this.dataContext = app.getDataContext();
        setQueryContext(dataContext);

        setupServices();
    }

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        switch (token) {
        case PATH_SYSMAN:
            target = new SysManager();
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        else
            return super.dispatch(previous, tokens, q);
    }

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
        // crawler.follow("TODO", null);
    }

    void setupServices() {
        serviceMap.install("service-map", serviceMap);
        serviceMap.install("session", new LoginManagerWs());
        serviceMap.install("ws-doc", new WsDocSite());

        setupDataIndex();
    }

    protected void setupDataIndex() {
        CoIndexServiceGroup group = new CoIndexServiceGroup(dataContext);
        serviceMap.install("data", group);
        serviceMap.install(group.getNameMap());
    }

}
