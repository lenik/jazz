package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class XwPar
        extends AbstractXwNode
        implements
            IXwHaveRuns {

    XWPFParagraph element;

//    public XwPar(XWPFParagraph element) {
//        this(null, element);
//    }

    public XwPar(IXwNode parent, XWPFParagraph element) {
        super(parent);
        if (parent == null)
            throw new NullPointerException("parent");
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.PARAGRAPH;
    }

    @Override
    public XWPFParagraph getElement() {
        return element;
    }

    @Override
    public XwRun addRun() {
        XWPFRun _run = element.createRun();
        return new XwRun(this, _run);
    }

    @Override
    public XwRun getRunToAppend() {
        List<XWPFRun> runs = element.getRuns();
        if (runs.isEmpty())
            return addRun();
        XWPFRun lastRun = runs.get(0);
        return new XwRun(this, lastRun);
    }

    @Override
    public void addPlainText(String text) {
        XwUtils.addPlainText(element, text);
    }

}
