package net.bodz.bas.tex.dom;

import net.bodz.bas.c.string.AbstractTextBlock;
import net.bodz.bas.io.ITreeOut;

public class TexDoc
        extends AbstractTextBlock {

    public final TexPreamble preamble;
    public final Title title;
    public final Body body;

    public TexDoc() {
        preamble = new TexPreamble();
        title = new Title();
        body = new Body();
    }

    @Override
    public void format(ITreeOut out) {
        preamble.format(out);
        out.println();
        out.println(title);
        out.println("\\begin{document}");
        // out.enter();
        body.format(out);
        // out.leave();
        out.println("\\end{document}");
    }

}
