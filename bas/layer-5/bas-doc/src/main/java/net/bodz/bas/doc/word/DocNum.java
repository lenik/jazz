package net.bodz.bas.doc.word;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr;

public class DocNum {

    BigInteger numId;

    Integer spaceAfterExceptLast;

    public DocNum(BigInteger numId) {
        this.numId = numId;
    }

    public void apply(XWPFParagraph paragraph) {
        apply(paragraph, 0, false);
    }

    public void apply(XWPFParagraph paragraph, int level) {
        apply(paragraph, level, false);
    }

    public void applyLast(XWPFParagraph paragraph) {
        apply(paragraph, 0, true);
    }

    public void applyLast(XWPFParagraph paragraph, int level) {
        apply(paragraph, level, true);
    }

    public void apply(XWPFParagraph paragraph, int level, boolean isLast) {
        paragraph.setNumID(numId);
        if (level > 0) {
            CTNumPr numProps = paragraph.getCTP().getPPr().getNumPr();
            CTDecimalNumber iLevel = numProps.addNewIlvl();
            iLevel.setVal(BigInteger.valueOf(level));
        }
        if (!isLast)
            if (spaceAfterExceptLast != null)
                paragraph.setSpacingAfter(spaceAfterExceptLast);
    }

}
