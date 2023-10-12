package net.bodz.bas.doc.node.conv;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import net.bodz.bas.doc.node.TextPar;
import net.bodz.bas.doc.word.xwpf.IXwNode;
import net.bodz.bas.doc.word.xwpf.XwPar;

public class XwpfItemBuilder
        extends WordConverter {

    int parIndex;

    public XwpfItemBuilder(IXwNode stackTop) {
        super(stackTop);
    }

    @Override
    public void textPar(TextPar textPar) {
        IXwNode top = stack.top();
        XwPar x_par = top.closest(xp.PAR);
        if (x_par == null)
            throw new NullPointerException("x_par");
        XWPFParagraph _par = x_par.getElement();

        if (parIndex++ != 0)
            _par.createRun().addBreak();

        stack.push(x_par);
        haveRuns(textPar);
        stack.pop();
    }

}
