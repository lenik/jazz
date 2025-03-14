package net.bodz.violet.server;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.org.ICrawlable;
import net.bodz.bas.site.org.ICrawler;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.site.LilyStartSite;

/**
 * @label OA Site Frame
 */
public class VioletSite
        extends LilyStartSite {

    static final Logger logger = LoggerFactory.getLogger(VioletSite.class);

//    TokenManager tokenManager = new TokenManager();
//    SessionInfo sessionInfo;

    public VioletSite(IDataApplication app) {
        super(app);
//        serviceMap.install("query", new QueryService(dataContext));
//        serviceMap.install("report", new TemplateService(dataContext, jobManager));

//        sessionInfo = new SessionInfo(dataContext);
//        serviceMap.install("session2", sessionInfo);
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

    @Override
    public Integer getMaxAge() {
        // since violet is transaction-based, max-age should commonly useless.
        return 0;
    }

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.NO_CACHE;
    }

    /**
     * TODO getSiteUrl...
     */
    @Override
    public String getSiteUrl() {
        return "http://zebra.bee32.com/oa";
    }

    /** ⇱ Implementation Of {@link IPathDispatchable}. */
    /* _____________________________ */static section.iface __PATH_DISP__;

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return super.dispatch(previous, tokens, q);
    }

    /** ⇱ Implementation Of {@link ICrawlable}. */
    /* _____________________________ */static section.iface __CRAWL__;

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
    }

}
