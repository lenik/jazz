package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.IHtmlTag;

public class HtmlThTag
        extends _HtmlThTag<HtmlThTag> {

    public HtmlThTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
     * True to show a radio. The radio column has fixed width.
     * 
     * @default false
     */
    public HtmlThTag dataRadio(String val) {
        attr("data-radio", val);
        return this;
    }

    /**
     * True to show a checkbox. The checkbox column has fixed width.
     * 
     * @default false
     */
    public HtmlThTag dataCheckbox(String val) {
        attr("data-checkbox", val);
        return this;
    }

    /**
     * The column field name.
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
        attr("data-field", val);
        return this;
    }

    /**
     * The column title text.
     */
    public HtmlThTag dataTitle(String val) {
        attr("data-title", val);
        return this;
    }

    /**
     * The column class name.
     */
    public HtmlThTag dataClass(String val) {
        attr("data-class", val);
        return this;
    }

    /**
     * Indicate how to align the column data. "left", "right", "center" can be used.
     */
    public HtmlThTag dataAlign(String val) {
        attr("data-align", val);
        return this;
    }

    /**
     * Indicate how to align the table header. "left", "right", "center" can be used.
     */
    public HtmlThTag dataHalign(String val) {
        attr("data-halign", val);
        return this;
    }

    /**
     * Indicate how to align the cell data. "top", "middle", "bottom" can be used.
     */
    public HtmlThTag dataValign(String val) {
        attr("data-valign", val);
        return this;
    }

    /**
     * The width of column. If not defined, the width will auto expand to fit its contents.
     */
    public HtmlThTag dataWidth(String val) {
        attr("data-width", val);
        return this;
    }

    /**
     * True to allow the column can be sorted.
     * 
     * @default false
     */
    public HtmlThTag dataSortable(boolean val) {
        attr("data-sortable", val);
        return this;
    }

    /**
     * The default sort order, can only be "asc" or "desc".
     * 
     * @default asc
     */
    public HtmlThTag dataOrder(String val) {
        attr("data-order", val);
        return this;
    }

    /**
     * False to hide the columns item.
     * 
     * @default true
     */
    public HtmlThTag dataVisible(String val) {
        attr("data-visible", val);
        return this;
    }

    /**
     * False to disable the switchable of columns item.
     * 
     * @default true
     */
    public HtmlThTag dataSwitchable(String val) {
        attr("data-switchable", val);
        return this;
    }

    /**
     * True to select checkbox or radiobox when the column is clicked.
     * 
     * @default true
     */
    public HtmlThTag dataClickToStart(String val) {
        attr("data-click-to-start", val);
        return this;
    }

    /**
     * The cell formatter function, take three parameters:
     * <ul>
     * <li>value: the field value.
     * <li>row: the row record data.
     * <li>index: the row index.
     * </ul>
     */
    public HtmlThTag dataFormatter(String val) {
        attr("data-formatter", val);
        return this;
    }

    /**
     * The cell events listener when you use formatter function, take three parameters:
     * <ul>
     * <li>event: the jQuery event.
     * <li>value: the field value.
     * <li>row: the row record data.
     * <li>index: the row index.
     * </ul>
     */
    public HtmlThTag dataEvents(String val) {
        attr("data-events", val);
        return this;
    }

    /**
     * The custom field sort function that used to do local sorting, take two parameters:
     * <ul>
     * <li>a: the first field value.
     * <li>b: the second field value.
     * </ul>
     */
    public HtmlThTag dataSorter(String val) {
        attr("data-sorter", val);
        return this;
    }

    /**
     * The cell style formatter function, take three parameters:
     * <ul>
     * <li>value: the field value.
     * <li>row: the row record data.
     * <li>index: the row index.
     * <li>Support classes or css.
     * </ul>
     */
    public HtmlThTag dataCellStyle(String val) {
        attr("data-cell-style", val);
        return this;
    }

    public HtmlThTag dataHeight(String val) {
        attr("data-height", val);
        return this;
    }

}
