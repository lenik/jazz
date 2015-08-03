package net.bodz.bas.site.org;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.util.IDateFormatConsts;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.bas.xml.dom.IXmlTag;

public class Sitemap_xml
        extends AbstractHtmlViewBuilder<Sitemap>
        implements IDateFormatConsts {

    static final String SITEMAP_NS = "http://www.sitemaps.org/schemas/sitemap/0.9";
    static final String XHTML_NS = "http://www.w3.org/1999/xhtml";

    public Sitemap_xml() {
        super(Sitemap.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, Sitemap value) {
        return ContentTypes.text_xml;
    }

    @Override
    public boolean isOrigin(Sitemap value) {
        return true;
    }

    @Override
    public IHtmlTag buildHtmlView(IHtmlViewContext ctx, IHtmlTag parent, IUiRef<Sitemap> ref, IOptions options)
            throws ViewBuilderException, IOException {
        Sitemap sitemap = ref.get();
        IXmlTag out = parent;

        out.pi("xml").xml("1.0", "utf-8");
        out = out.insert("urlset")//
                .attr("xmlns", SITEMAP_NS) //
                .attr("xmlns:xhtml", XHTML_NS);

        for (SitemapEntry entry : sitemap) {
            IXmlTag tag = out.insert("url");
            tag.insert("loc").text(entry.getUrl());

            for (Entry<String, String> alternate : entry.getAlternates().entrySet())
                tag.insert("xhtml:link") //
                        .attr("rel", "alternate") //
                        .attr("hreflang", alternate.getKey()) //
                        .attr("href", alternate.getValue());
            tag.insert("lastmod").text(ISO8601.format(entry.getLastModified()));
            tag.insert("changefreq").text(entry.getChangeFreq());
            tag.insert("priority").text(entry.getPriority());
        }
        return parent;
    }

}
