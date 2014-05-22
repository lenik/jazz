package net.bodz.bas.site.org;

import java.util.LinkedHashMap;
import java.util.Map;

public class SitemapEntry {

    private String url;
    private Map<String, String> alternates = new LinkedHashMap<>();
    private long lastModified;
    private ChangeFreq changeFreq = ChangeFreq.ALWAYS;
    private double priority;

    /**
     * Provides the full URL of the page, including the protocol (e.g. http, https) and a trailing
     * slash, if required by the site's hosting server. This value must be less than 2,048
     * characters.
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getAlternates() {
        return alternates;
    }

    /**
     * The date that the file was last modified, in ISO 8601 format. This can display the full date
     * and time or, if desired, may simply be the date in the format YYYY-MM-DD.
     */
    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * "Always" is used to denote documents that change each time that they are accessed. "Never" is
     * used to denote archived URLs (i.e. files that will not be changed again).
     */
    public ChangeFreq getChangeFreq() {
        return changeFreq;
    }

    public void setChangeFreq(ChangeFreq changeFreq) {
        this.changeFreq = changeFreq;
    }

    /**
     * The priority of that URL relative to other URLs on the site. This allows webmasters to
     * suggest to crawlers which pages are considered more important.
     */
    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

}
