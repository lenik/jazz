package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.BasicSite;
import net.bodz.bas.site.org.ICrawler;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.codegen.doc.WsDocSite;
import net.bodz.lily.security.login.LoginManager;
import net.bodz.lily.security.login.LoginManagerWso;

public abstract class LilyStartSite
        extends BasicSite {

    static final Logger logger = LoggerFactory.getLogger(LilyStartSite.class);

    protected final DataContext dataContext;

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

        return super.dispatch(previous, tokens, q);
    }

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
        // crawler.follow("TODO", null);
    }

    void setupServices() {
        CoIndexServiceGroup group = new CoIndexServiceGroup(dataContext);
        pathMap.install("data", group);
        pathMap.install(group.getNameMap());

        LoginManager loginManager = new LoginManager(dataContext);
        pathMap.install("session", new LoginManagerWso(loginManager));
        pathMap.install("ws-doc", new WsDocSite());
    }

}
