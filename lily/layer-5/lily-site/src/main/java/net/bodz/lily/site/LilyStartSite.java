package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
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
import net.bodz.lily.security.login.ILoginManager;
import net.bodz.lily.security.login.LoginManager;
import net.bodz.lily.security.login.LoginManagerWs;
import net.bodz.lily.tool.wsdoc.WsDocSite;

public abstract class LilyStartSite
        extends BasicSite {

    static final Logger logger = LoggerFactory.getLogger(LilyStartSite.class);

    public static final String PATH_SYSMAN = "sysmgr";

    protected final DataContext dataContext;
    protected LoginManager loginManager;

    public LilyStartSite() {
        this(DataHub.getPreferredHub().getMain());
    }

    public LilyStartSite(DataContext dataContext) {
        this.dataContext = dataContext;
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
        loginManager = new LoginManager(dataContext);
        serviceMap.install("session", new LoginManagerWs(loginManager));
        serviceMap.install("ws-doc", new WsDocSite());

        setAttribute(ILoginManager.ATTRIBUTE_NAME, loginManager);

        setupDataIndex();
    }

    protected void setupDataIndex() {
        CoIndexServiceGroup group = new CoIndexServiceGroup(dataContext);
        serviceMap.install("data", group);
        serviceMap.install(group.getNameMap());
    }

}
