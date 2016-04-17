package net.bodz.bas.html.io.mod.table;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.HtmlTh;
import net.bodz.bas.meta.codegen.CopyAndPaste;

@CopyAndPaste(ModTd.class)
public class ModTh
        extends HtmlTh {

    private StringBuilder sb;

    public ModTh(HtmlDoc doc, StringBuilder sb) {
        super(doc);
        if (sb == null)
            throw new NullPointerException("sb");
        this.sb = sb;
    }

    @Override
    public ModTh text(String str) {
        super.text(str); // -> directs to null html out.
        sb.append(str);
        return this;
    }

}
