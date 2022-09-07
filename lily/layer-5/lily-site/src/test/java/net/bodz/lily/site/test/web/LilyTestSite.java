package net.bodz.lily.site.test.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.ServiceTargetException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.file.UploadHandler;
import net.bodz.bas.site.org.ICrawlable;
import net.bodz.bas.site.org.ICrawler;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.site.LilyStartSite;

/**
 * @label OA Site Frame
 */
public class LilyTestSite
        extends LilyStartSite {

    static final Logger logger = LoggerFactory.getLogger(LilyTestSite.class);

    public static final String PATH_EVENT_LOGS = "logger";

    public LilyTestSite() {
        super(DataHub.getPreferredHub().getMain());

        serviceMap.install("mapper", new MapperService(dataContext));
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

    @Override
    public int getMaxAge() {
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
        HttpServletRequest request = CurrentHttpService.getRequest();

        Object target = null;
        String token = tokens.peek();
        if (token == null)
            return null;

        switch (token) {
        case PATH_UPLOAD:
            File localDir = LilyTestDirs.getInstance().getUploadDir(request);
            IAnchor getAnchor = LilyTestDirs.getInstance().getUploadedAnchor(request);
            UploadHandler uploadHandler = new UploadHandler(localDir, getAnchor);
            try {
                target = uploadHandler.handlePostRequest(request);
            } catch (Exception e) {
                throw new ServiceTargetException("upload handler: " + e.getMessage(), e);
            }
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        else
            return super.dispatch(previous, tokens, q);
    }

    /** ⇱ Implementation Of {@link ICrawlable}. */
    /* _____________________________ */static section.iface __CRAWL__;

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
    }

}
