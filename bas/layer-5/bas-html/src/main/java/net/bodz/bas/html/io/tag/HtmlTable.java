package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlTable;

public class HtmlTable
        extends _HtmlTable<HtmlTable> {

    public HtmlTable(HtmlDoc doc) {
        super(doc);
    }

    public HtmlTable align(Object val) {
        attr("align", val);
        return this;
    }

    public HtmlTable dataToggle(Object val) {
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
    public HtmlTable dataUrl(Object val) {
        attr("data-url", val);
        return this;
    }

    public HtmlTable width(Object val) {
        attr("width", val);
        return this;
    }

}
