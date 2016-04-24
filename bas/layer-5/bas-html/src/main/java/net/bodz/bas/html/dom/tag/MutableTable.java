package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.tag.gen._MutableTable;

public class MutableTable
        extends _MutableTable<MutableTable> {

    public MutableTable(IHtmlTag parent) {
        super(parent);
    }

    public MutableTable align(Object val) {
        attr("align", val);
        return this;
    }

    public MutableTable dataToggle(Object val) {
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
    public MutableTable dataUrl(Object val) {
        attr("data-url", val);
        return this;
    }

    public MutableTable width(Object val) {
        attr("width", val);
        return this;
    }

}
