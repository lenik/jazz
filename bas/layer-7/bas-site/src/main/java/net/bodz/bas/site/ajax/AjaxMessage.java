package net.bodz.bas.site.ajax;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.builtin.Throwable_htm;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.ui.dom1.UiVar;

public class AjaxMessage
        extends BCharOut
        implements IJsonSerializable {

    public static final String MSGBOX = "msgbox";
    public static final String NOTICE = "notice";
    public static final String INLINE = "inline";
    public static final String LOG = "log";

    String type = MSGBOX;
    String title;
    String width;
    String height;

    String level;
    String css;
    IHtmlOut htmlOut;

    public AjaxMessage(String type) {
        this.type = type;
    }

    /**
     * Or a named context/position where the inline message will go.
     *
     * @see #MSGBOX
     * @see #NOTICE
     * @see #INLINE
     * @see #LOG
     */
    public String getType() {
        return type;
    }

    public AjaxMessage type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Can be: error, warn, info
     */
    public String getLevel() {
        return level;
    }

    public AjaxMessage level(String level) {
        this.level = level;
        return this;
    }

    public AjaxMessage error() {
        level("error");
        return this;
    }

    public AjaxMessage warn() {
        level("warn");
        return this;
    }

    public AjaxMessage info() {
        level("info");
        return this;
    }

    /**
     * User style class.
     */
    public String getCss() {
        return css;
    }

    public AjaxMessage css(String css) {
        this.css = css;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AjaxMessage title(String title) {
        this.title = title;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public AjaxMessage width(Object width) {
        this.width = width == null ? null : width.toString();
        return this;
    }

    public String getHeight() {
        return height;
    }

    public AjaxMessage height(Object height) {
        this.height = height == null ? null : height.toString();
        return this;
    }

    public boolean isHtml() {
        return htmlOut != null;
    }

    public IHtmlOut html() {
        if (htmlOut == null) {
            HtmlOutputFormat format = new HtmlOutputFormat();
            HtmlDoc doc = new HtmlDoc(TreeOutImpl.from(this), format);
            htmlOut = doc.newHtmlOut();
        }
        return htmlOut;
    }

    public AjaxMessage errorHtml(String message, Throwable e) {
        error();
        width("80%");
        height("75%");
        title(message);
        Throwable_htm vb = new Throwable_htm();
        vb.setTitle(message);
        vb.buildHtmlViewStart(null, html(), UiVar.wrap(e));
        return this;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void writeObject(IJsonOut out) {
        out.object();

        if (type != null)
            out.entry("type", type);
        if (level != null)
            out.entry("level", level);
        if (css != null)
            out.entry("css", css);
        if (title != null)
            out.entry("title", title);
        if (width != null)
            out.entry("width", width);
        if (height != null)
            out.entry("height", height);

        if (htmlOut != null) {
            htmlOut.flush();
            out.entry("html", this.toString());
        } else
            out.entry("text", this.toString());

        out.endObject();
    }

}
