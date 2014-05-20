package net.bodz.bas.site.org;

import net.bodz.bas.repr.content.IContent;

public class SitemapGenerator {

    String siteUrl;
    Sitemap sitemap = new Sitemap();
    double levelWeight = -0.1;

    public SitemapGenerator(String siteUrl) {
        if (siteUrl == null)
            throw new NullPointerException("siteUrl");

        while (siteUrl.endsWith("/"))
            siteUrl = siteUrl.substring(0, siteUrl.length() - 1);

        this.siteUrl = siteUrl;
    }

    public double getLevelWeight() {
        return levelWeight;
    }

    public void setLevelWeight(double levelWeight) {
        this.levelWeight = levelWeight;
    }

    public Sitemap traverse(IContent siteObj) {
        Crawler crawler = new Crawler(siteUrl, 0);
        crawler.follow("", siteObj);
        sitemap.normalizePriorities(0.1, 0.9);
        return sitemap;
    }

    class Crawler
            implements ICrawler {

        private String prefix;
        private int level;

        public Crawler(String prefix, int level) {
            this.prefix = prefix;
            this.level = level;
        }

        @Override
        public void follow(String subpath, IContent contentInfo) {
            SitemapEntry entry = new SitemapEntry();
            entry.setUrl(prefix + subpath);
            entry.setLastModified(contentInfo.getLastModified().getTime());
            entry.setChangeFreq(ChangeFreq.fromMaxAge(contentInfo.getMaxAge()));
            entry.setPriority(level * levelWeight + contentInfo.getPriority());
            sitemap.add(entry);

            if (contentInfo instanceof ICrawlable) {
                ICrawlable child = (ICrawlable) contentInfo;
                Crawler childCrawler = new Crawler(prefix + subpath + "/", level + 1);
                child.crawlableIntrospect(childCrawler);
            }
        }

    }

}
