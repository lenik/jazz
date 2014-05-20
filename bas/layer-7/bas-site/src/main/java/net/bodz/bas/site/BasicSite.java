package net.bodz.bas.site;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.repr.content.AbstractXjdocContent;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.org.ICrawlable;
import net.bodz.bas.site.org.SiteGraph;
import net.bodz.bas.site.org.Sitemap;
import net.bodz.bas.site.org.SitemapGenerator;

public abstract class BasicSite
        extends AbstractXjdocContent
        implements IPathDispatchable, ICrawlable {

    public abstract String getHomeUrl();

    public Sitemap getSitemap() {
        return new SitemapGenerator(getHomeUrl()).traverse(this);
    }

    public SiteGraph getSiteGraph() {
        SiteGraph graph = new SiteGraph();
        graph.setName(getHomeUrl());
        graph.setHref("");
        graph.setXjdoc(getXjdoc());
        crawlableIntrospect(graph);
        return graph;
    }

    /** ⇱ Implementation Of {@link IPathDispatchable}. */
    /* _____________________________ */static section.iface __PATH_DISP__;

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        String token = tokens.peek();
        switch (token) {
        case "sitemap.xml":
            return PathArrival.shift(previous, getSitemap(), tokens);

        case "robots.txt":
            break;

        case "intl":
            String lang = tokens.peek(1);
            if (lang != null) {
                HttpServletRequest request = CurrentHttpService.getRequest();
                request.setAttribute("LOCALE", lang);
            }
            break;
        }
        return null;
    }

}
