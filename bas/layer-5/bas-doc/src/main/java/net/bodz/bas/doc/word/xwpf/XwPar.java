package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

public class XwPar
        extends AbstractXwNode
        implements
            IXwHaveRuns {

    XWPFParagraph _paragraph;

//    public XwPar(XWPFParagraph element) {
//        this(null, element);
//    }

    public XwPar(IXwNode parent, XWPFParagraph _paragraph) {
        super(parent);
        if (parent == null)
            throw new NullPointerException("parent");
        if (_paragraph == null)
            throw new NullPointerException("_paragraph");
        this._paragraph = _paragraph;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.PARAGRAPH;
    }

    @Override
    public XWPFParagraph getElement() {
        return _paragraph;
    }

    @Override
    public XwRun addRun() {
        XWPFRun _run = _paragraph.createRun();
        return new XwRun(this, _run);
    }

    @Override
    public XwRun getRunToAppend() {
        List<XWPFRun> runs = _paragraph.getRuns();
        if (runs.isEmpty())
            return addRun();
        XWPFRun lastRun = runs.get(0);
        return new XwRun(this, lastRun);
    }

    @Override
    public void addPlainText(String text) {
        XwUtils.addPlainText(_paragraph, text);
    }

    public XWPFRun createField(String code) {
        XWPFRun run1 = _paragraph.createRun();
        CTR cTR1 = run1.getCTR();
        CTFldChar BEGIN = CTFldChar.Factory.newInstance();
        BEGIN.setFldCharType(STFldCharType.BEGIN);
        cTR1.setFldCharArray(new CTFldChar[] { BEGIN });

        XWPFRun run2 = _paragraph.createRun();
        CTR cTR2 = run2.getCTR();
        CTText cTText = CTText.Factory.newInstance();
        cTText.setStringValue(code);
        cTR2.setInstrTextArray(new CTText[] { cTText });

        XWPFRun run3 = _paragraph.createRun();
        CTR cTR3 = run3.getCTR();
        CTFldChar END = CTFldChar.Factory.newInstance();
        END.setFldCharType(STFldCharType.END);
        cTR3.setFldCharArray(new CTFldChar[] { END });

        return run1;
    }

}
