package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlTableTag
        extends _HtmlTableTag<HtmlTableTag> {

    public HtmlTableTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public HtmlTableTag dataToggle(String val) {
        attr("data-toggle", val);
        return this;
    }

    /**
     * Bootstrap: Example
     * 
     * <pre>
     * &lt;table data-url="data.json" data-toggle="table" &gt;
     *     &lt;thead&gt;
     *      &lt;tr&gt;
     *          &lt;th data-field="firstname" ...&gt;
     *      &lt;/tr&gt;
     *     &lt;/thead&gt;
     * &lt;/table&gt;
     */
    public HtmlTableTag dataUrl(String val) {
        attr("data-url", val);
        return this;
    }

    public HtmlTableTag width(String val) {
        attr("width", val);
        return this;
    }

}
