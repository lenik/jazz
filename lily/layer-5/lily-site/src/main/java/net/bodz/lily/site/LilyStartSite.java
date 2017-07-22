package net.bodz.lily.site;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.BasicSite;
import net.bodz.bas.site.org.ICrawler;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VhostDataContexts;

public abstract class LilyStartSite
        extends BasicSite {

    static final Logger logger = LoggerFactory.getLogger(LilyStartSite.class);

    IVirtualHost vhost;
    DataContext dataContext;

    public LilyStartSite(IVirtualHost vhost) {
        this.vhost = vhost;
        this.dataContext = VhostDataContexts.getInstance().get(vhost);
        setQueryContext(dataContext);

        setupServices();
    }

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        return super.dispatch(previous, tokens);
    }

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
        // crawler.follow("TODO", null);
    }

    void setupServices() {
        pathMap.install(new CoIndexServiceGroup(dataContext).getNameMap());
    }

}
