package net.bodz.bas.doc.word;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import net.bodz.bas.doc.node.util.Css3ListStyle;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.ui.css3.property.ListStyleTypeMode;

public class XwNumbering {

    XWPFDocument document;
    XWPFNumbering numbering;
    BigInteger maxId;

    Map<BigInteger, CTAbstractNum> map = new TreeMap<>();

    public XwNumbering(XWPFDocument document) {
        this.document = document;
        this.numbering = document.createNumbering(); // get or create.

        for (XWPFAbstractNum abstractNum : numbering.getAbstractNums()) {
            CTAbstractNum ct = abstractNum.getCTAbstractNum();
            BigInteger id = ct.getAbstractNumId();
            map.put(id, ct);
            if (maxId == null || maxId.compareTo(id) < 0)
                maxId = id;
        }
    }

    protected BigInteger getNextId() {
        BigInteger nextId;
        if (maxId == null)
            nextId = BigInteger.ONE;
        else
            nextId = maxId.add(BigInteger.ONE);
        maxId = nextId;
        return nextId;
    }

    /**
     * @return numID
     */
    public BigInteger compile(IListStyle... styles) {
        return compile(Arrays.asList(styles));
    }

    /**
     * @return numID
     */
    public BigInteger compile(List<IListStyle> styles) {
        if (styles == null)
            throw new NullPointerException("styles");

        BigInteger id = getNextId();
        CTAbstractNum ct = CTAbstractNum.Factory.newInstance();
        ct.setAbstractNumId(id);

        int n = styles.size();
        StringBuilder prefix = new StringBuilder();
        for (int level = 0; level < n; level++) {
            IListStyle style = styles.get(level);

            CTLvl cTLvl = ct.addNewLvl();
            cTLvl.setIlvl(BigInteger.valueOf(level)); // set indent level 0

            if (style.isOrdered()) {
                CTNumFmt numFmt = cTLvl.addNewNumFmt();
                STNumberFormat.Enum val = numFmt.getVal();
                if (style.isCss3()) {
                    Css3ListStyle css3 = (Css3ListStyle) style;
                    val = css2numFmt.get(css3.getMode());
                    if (val == null)
                        throw new UnexpectedException("val");
                }
                numFmt.setVal(val);

                CTLevelText levelText = cTLvl.addNewLvlText();
                if (level > 1)
                    prefix.append('.');
                prefix.append('%');
                prefix.append(level + 1);
                if (level == 0)
                    prefix.append('.');
                levelText.setVal(prefix.toString());
            } else {
                CTLevelText levelText = cTLvl.addNewLvlText();
                String example = style.format(0);
                levelText.setVal(example);
            }

            if (!style.isContinuous()) {
                cTLvl.addNewStart().setVal(style.getStartNumber());
            }

            HorizAlignment justify = style.getJustify();
            if (justify != null)
                cTLvl.addNewLvlJc().setVal(toJc(justify));

            MeasureLength left = style.getLeft();
            MeasureLength hanging = style.getHanging();
            MeasureLength tabPosition = style.getTabPosition();
            if (left != null || hanging != null || tabPosition != null) {
                CTPPrGeneral pPr = cTLvl.addNewPPr();
                if (tabPosition != null) {
                    CTTabs tabs = pPr.addNewTabs();
                    CTTabStop tabStop = tabs.addNewTab();
                    tabStop.setVal(STTabJc.NUM);
                    tabStop.setPos(tabPosition.toTwips());
                } else {
                    cTLvl.addNewSuff().setVal(STLevelSuffix.SPACE);
                }
                if (left != null || hanging != null) {
                    CTInd ind = pPr.addNewInd();
                    if (left != null)
                        ind.setLeft(left.toTwips());
                    if (hanging != null)
                        ind.setHanging(hanging.toTwips());
                }
            }

        }

        XWPFAbstractNum abstractNum = new XWPFAbstractNum(ct, numbering);
        numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(id);

        return numID;
    }

    static final Map<ListStyleTypeMode, STNumberFormat.Enum> css2numFmt = new HashMap<>();
    static {
        css2numFmt.put(ListStyleTypeMode.decimal, STNumberFormat.DECIMAL);
        css2numFmt.put(ListStyleTypeMode.decimal_leading_zero, STNumberFormat.DECIMAL_ZERO);
        css2numFmt.put(ListStyleTypeMode.lower_roman, STNumberFormat.LOWER_ROMAN);
        css2numFmt.put(ListStyleTypeMode.upper_roman, STNumberFormat.UPPER_ROMAN);
        css2numFmt.put(ListStyleTypeMode.lower_latin, STNumberFormat.LOWER_LETTER);
        css2numFmt.put(ListStyleTypeMode.lower_alpha, STNumberFormat.LOWER_LETTER);
        css2numFmt.put(ListStyleTypeMode.upper_latin, STNumberFormat.UPPER_LETTER);
        css2numFmt.put(ListStyleTypeMode.upper_alpha, STNumberFormat.UPPER_LETTER);
    }

    static STJc.Enum toJc(HorizAlignment align) {
        switch (align) {
        case LEFT:
            return STJc.LEFT;
        case RIGHT:
            return STJc.RIGHT;
        case CENTER:
            return STJc.CENTER;
        default:
            return STJc.LEFT;
        }
    }

}
