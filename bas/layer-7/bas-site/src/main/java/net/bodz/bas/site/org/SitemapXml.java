package net.bodz.bas.site.org;

import java.io.IOException;

import net.bodz.bas.c.java.util.IDateFormatConsts;
import net.bodz.bas.html.AbstractHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.io.xml.IXmlOut;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class SitemapXml
        extends AbstractHtmlViewBuilder<Sitemap>
        implements IDateFormatConsts {

    static final String SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9";

    public SitemapXml() {
        super(Sitemap.class);
    }

    @Override
    public ContentType getContentType(Sitemap value) {
        return ContentTypes.text_xml;
    }

    @Override
    public boolean isOrigin(Sitemap value) {
        return true;
    }

    @Override
    public IHtmlViewContext buildHtmlView(IHtmlViewContext ctx, IUiRef<Sitemap> ref, IOptions options)
            throws ViewBuilderException, IOException {
        Sitemap sitemap = ref.get();
        IXmlOut out = ctx.getOut();

        out._xml_pi("1.0", "utf-8");
        out.start("urlset").attr("xmlns", SITEMAP_NS);

        for (SitemapEntry entry : sitemap) {
            out.start("url");
            out.tag("loc").text(entry.getUrl());
            out.tag("lastmod").text(ISO8601.format(entry.getLastModified()));
            out.tag("changefreq").text(entry.getChangeFreq());
            out.tag("priority").text(entry.getPriority());
            out.end();
        }

        out.end();
        return ctx;
    }

}
