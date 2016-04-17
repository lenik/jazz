package net.bodz.bas.html.io.mod.table;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlTd;

public class ModTd
        extends HtmlTd {

    private StringBuilder sb;

    public ModTd(HtmlDoc doc, StringBuilder sb) {
        super(doc);
        if (sb == null)
            throw new NullPointerException("sb");
        this.sb = sb;
    }

    @Override
    public ModTd text(String str) {
        super.text(str); // -> directs to null html out.
        sb.append(str);
        return this;
    }

}
