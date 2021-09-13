package net.bodz.bas.tex.dom;

import java.io.IOException;

import net.bodz.bas.c.string.IIndentedForm;
import net.bodz.bas.io.ITreeOut;

public class TeXDoc
        implements
            IIndentedForm {

    public final TeXPreamble preamble;
    public final Title title;
    public final Body body;

    public TeXDoc() {
        preamble = new TeXPreamble();
        title = new Title();
        body = new Body();
    }

    @Override
    public void writeObject(ITreeOut out)
            throws IOException {
        preamble.writeObject(out);
        out.println();
        out.println(title);
        out.println("\\begin{document}");
        // out.enter();
        body.writeObject(out);
        // out.leave();
        out.println("\\end{document}");
    }

}
