package net.bodz.bas.html.dom;

import java.util.List;
import java.util.Map;

import net.bodz.bas.html.artifact.IMutableArtifactDependent;
import net.bodz.bas.html.dom.tag.MutableHead;
import net.bodz.bas.html.dom.tag.MutableLink;
import net.bodz.bas.html.dom.tag.MutableScript;
import net.bodz.bas.html.dom.tag.MutableStyle;
import net.bodz.bas.http.model.IHttpHeadData;

public interface IHtmlHeadData
        extends IHttpHeadData, IMutableArtifactDependent {

    String getTitle();

    void setTitle(String title);

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

    /**
     * You can merge the optional head into your real head.
     */
    MutableHead getOptHead();

    /**
     * The links should be placed after html artifacts.
     */
    List<MutableLink> getLinks();

    void addLink(MutableLink link);

    /**
     * The scripts should be placed after html artifacts.
     */
    List<MutableScript> getScripts();

    void addScript(MutableScript script);

    /**
     * The styles should be placed after html artifacts.
     */
    List<MutableStyle> getStyles();

    void addStyle(MutableStyle style);

}
