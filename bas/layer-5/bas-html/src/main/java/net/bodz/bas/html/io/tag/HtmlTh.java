package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlTh;

public class HtmlTh
        extends _HtmlTh<HtmlTh> {

    public HtmlTh(HtmlDoc doc) {
        super(doc);
    }

    /**
     * True to show a radio. The radio column has fixed width.
     * 
     * @default false
     */
    public HtmlTh dataRadio(Object val) {
        attr("data-radio", val);
        return this;
    }

    /**
     * True to show a checkbox. The checkbox column has fixed width.
     * 
     * @default false
     */
    public HtmlTh dataCheckbox(Object val) {
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
    public HtmlTh dataField(Object val) {
        attr("data-field", val);
        return this;
    }

    /**
     * The column title text.
     */
    public HtmlTh dataTitle(Object val) {
        attr("data-title", val);
        return this;
    }

    /**
     * The column class name.
     */
    public HtmlTh dataClass(Object val) {
        attr("data-class", val);
        return this;
    }

    /**
     * Indicate how to align the column data. "left", "right", "center" can be used.
     */
    public HtmlTh dataAlign(Object val) {
        attr("data-align", val);
        return this;
    }

    /**
     * Indicate how to align the table header. "left", "right", "center" can be used.
     */
    public HtmlTh dataHalign(Object val) {
        attr("data-halign", val);
        return this;
    }

    /**
     * Indicate how to align the cell data. "top", "middle", "bottom" can be used.
     */
    public HtmlTh dataValign(Object val) {
        attr("data-valign", val);
        return this;
    }

    /**
     * The width of column. If not defined, the width will auto expand to fit its contents.
     */
    public HtmlTh dataWidth(Object val) {
        attr("data-width", val);
        return this;
    }

    /**
     * True to allow the column can be sorted.
     * 
     * @default false
     */
    public HtmlTh dataSortable(boolean val) {
        attr("data-sortable", val);
        return this;
    }

    /**
     * The default sort order, can only be "asc" or "desc".
     * 
     * @default asc
     */
    public HtmlTh dataOrder(Object val) {
        attr("data-order", val);
        return this;
    }

    /**
     * False to hide the columns item.
     * 
     * @default true
     */
    public HtmlTh dataVisible(Object val) {
        attr("data-visible", val);
        return this;
    }

    /**
     * False to disable the switchable of columns item.
     * 
     * @default true
     */
    public HtmlTh dataSwitchable(Object val) {
        attr("data-switchable", val);
        return this;
    }

    /**
     * True to select checkbox or radiobox when the column is clicked.
     * 
     * @default true
     */
    public HtmlTh dataClickToStart(Object val) {
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
    public HtmlTh dataFormatter(Object val) {
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
    public HtmlTh dataEvents(Object val) {
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
    public HtmlTh dataSorter(Object val) {
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
    public HtmlTh dataCellStyle(Object val) {
        attr("data-cell-style", val);
        return this;
    }

    public HtmlTh dataHeight(Object val) {
        attr("data-height", val);
        return this;
    }

}
