package net.bodz.bas.html.viz;

import java.util.Map;

import net.bodz.bas.html.artifact.IMutableArtifactDependent;

public interface IHtmlHeadData
        extends IMutableArtifactDependent {

    String getTitle();

    void setTitle(String title);

    String HTTP_CONTENT_TYPE = "content-type";
    String HTTP_DEFAULT_STYLE = "default-style";
    String HTTP_REFRESH = "refresh";

    Map<String, String> getHttpEquivMetaMap();

    String getHttpEquivMeta(String httpEquiv);

    void setHttpEquivMeta(String httpEquiv, String content);

    String META_APPLICATION_NAME = "application-name";
    String META_AUTHOR = "author";

    /**
     * Meta descriptions can be any length, but search engines generally truncate snippets longer
     * than 160 characters. It is best to keep meta descriptions between 150 and 160 characters.
     *
     * Note that Google and other search engines bold keywords in the description when they match
     * search queries.
     */
    String META_DESCRIPTION = "description";
    String META_GENERATOR = "generator";

    /**
     * Nowadays, however, major search engines don't really bother with this tag.
     */
    String META_KEYWORDS = "keywords";

    String META_GOOGLE_SITE_VERIFICATION = "google-site-verification";

    /**
     * The default values are "index, follow" (the same as "all").
     * <ul>
     * <li>noindex: prevents the page from being indexed</li>
     * <li>nofollow: prevents the Googlebot from following links from this page</li>
     * <li>nosnippet: prevents a snippet from being shown in the search results</li>
     * <li>noodp: prevents the alternative description from the ODP/DMOZ from being used</li>
     * <li>noarchive: prevents Google from showing the <strong>Cached</strong> link for a page.</li>
     * <li>unavailable_after:[date]: lets you specify the exact time and date you want to stop
     * crawling and indexing of this page</li>
     * <li>noimageindex: lets you specify that you do not want your page to appear as the referring
     * page for an image that appears in Google search results.</li>
     * <li>none: is equivalent to <code>noindex, nofollow</code>.</li>
     * </ul>
     */
    String META_ROBOTS = "robots";

    /**
     * The Revisit Tag defines after how many days or months the search engine spider should revisit
     * your website.
     *
     * Example: <code>3 month</code>.
     */
    String META_REVISIT = "revisit-after";

    String META_VIEWPORT = "viewport";

    Map<String, String> getMetaMap();

    String getMeta(String name);

    void setMeta(String name, String content);

}
