package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlThTag
        extends _HtmlThTag<HtmlThTag> {

    public HtmlThTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
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
    public HtmlThTag dataField(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("data-field", val);
        return this;
    }

    public HtmlThTag dataAlign(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("data-align", val);
        return this;
    }

    /** header align */
    public HtmlThTag dataHalign(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("data-halign", val);
        return this;
    }

    public HtmlThTag dataHeight(String val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("data-height", val);
        return this;
    }

    public HtmlThTag dataSortable(boolean val) {
        attr("data-sortable", val);
        return this;
    }

}
