package net.bodz.bas.site;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.JazzBasProject;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.i18n.LocaleCtl;
import net.bodz.bas.repr.content.AbstractXjdocContent;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.site.org.ICrawlable;
import net.bodz.bas.site.org.SiteGraph;
import net.bodz.bas.site.org.Sitemap;
import net.bodz.bas.site.org.SitemapGenerator;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.project.IJazzModule;

public abstract class BasicSite
        extends AbstractXjdocContent
        implements IQueryable, IPathDispatchable, ICrawlable {

    private IQueryable queryContext;
    private Map<String, IJazzModule> modules = new TreeMap<>();
    protected final Map<String, Object> pathMap;

    public BasicSite() {
        for (IJazzModule module : JazzBasProject.getInstance().getModules()) {
            String name = module.getName();
            name = Strings.hyphenatize(name);
            modules.put(name, module);
        }
        pathMap = new HashMap<>();
    }

    public IQueryable getQueryContext() {
        return queryContext;
    }

    public void setQueryContext(IQueryable queryContext) {
        this.queryContext = queryContext;
    }

    public Map<String, IJazzModule> getModules() {
        return modules;
    }

    public String getSiteUrl() {
        StringBuilder sb = new StringBuilder();
        HttpServletRequest request = CurrentHttpService.getRequest();

        sb.append(request.getScheme());
        sb.append("://");

        String forwardedFor = request.getHeader("X-Forwarded-For");
        String forwardedServer = request.getHeader("X-Forwarded-Server");
        if ("127.0.0.1".equals(forwardedFor) && forwardedServer != null) {
            sb.append(forwardedServer);
        } else {
            sb.append(request.getServerName());
            if (request.getServerPort() != 80) {
                sb.append(":");
                sb.append(request.getServerPort());
            }
        }

        sb.append(request.getContextPath());
        return sb.toString();
    }

    protected String getStartUrl() {
        String url = getSiteUrl();

        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null) {
            Locale locale = (Locale) request.getAttribute(LocaleCtl.LOCALE.getName());
            if (locale != null)
                url += "/intl/" + locale.toLanguageTag().toLowerCase();
        }

        return url;
    }

    protected Map<String, String> getAlternateUrls() {
        return Collections.emptyMap();
    }

    public Sitemap getSitemap() {
        String siteUrl = getStartUrl();
        Map<String, String> alternateUrls = getAlternateUrls();
        return new SitemapGenerator(siteUrl, alternateUrls).traverse(this);
    }

    public SiteGraph getSiteGraph() {
        SiteGraph graph = new SiteGraph();
        graph.setName(getSiteUrl());
        graph.setHref(getStartUrl());
        graph.setXjdoc(getXjdoc());
        crawlableIntrospect(graph);
        return graph;
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

    @Override
    public int getMaxAge() {
        return 3600;
    }

    /** ⇱ Implementation Of {@link IQueryable}. */
    /* _____________________________ */static section.iface __QUERYABLE__;

    @Override
    public Object query(Object specification)
            throws QueryException {
        if (queryContext != null)
            return queryContext.query(specification);
        return null;
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        if (queryContext != null)
            return queryContext.query(specificationId);
        return null;
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        if (queryContext != null)
            return queryContext.query(specificationType);
        return null;
    }

    /** ⇱ Implementation Of {@link IPathDispatchable}. */
    /* _____________________________ */static section.iface __PATH_DISP__;

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        String token = tokens.peek();

        if (pathMap != null) {
            Object target = pathMap.get(token);
            if (target != null)
                return PathArrival.shift(previous, target, tokens);
        }

        switch (token) {
        case "sitemap.xml":
            return PathArrival.shift(previous, getSitemap(), tokens);

        case "robots.txt":
            break;

        case "intl":
            String lang = tokens.peek(1);
            if (lang != null) {
                HttpServletRequest request = CurrentHttpService.getRequest();
                Locale locale = Locale.forLanguageTag(lang); // non-null.
                request.setAttribute(LocaleCtl.LOCALE.getName(), locale);
                return PathArrival.shift(2, previous, this, tokens);
            }
            break;
        }
        return null;
    }

}
