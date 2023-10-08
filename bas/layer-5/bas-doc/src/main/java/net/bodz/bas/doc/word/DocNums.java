package net.bodz.bas.doc.word;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;

public class DocNums {

    final XWPFDocument document;
    XWPFNumbering numbering;

    Map<String, BigInteger> numStyleIds = new HashMap<>();

    public DocNums(XWPFDocument document) {
        if (document == null)
            throw new NullPointerException("document");
        this.document = document;
        this.numbering = document.createNumbering();
    }

    public synchronized BigInteger getNumStyleId(String name) {
        BigInteger numStyleId = numStyleIds.get(name);
        if (numStyleId == null) {
            XWPFAbstractNum numStyle = AbstractNum.getNumStyle(name);
            if (numStyle == null)
                throw new IllegalArgumentException("undefined num-style name: " + name);

            numStyleId = numbering.addAbstractNum(numStyle);
            numStyleIds.put(name, numStyleId);
        }
        return numStyleId;
    }

    public DocNum addNum(String numStyleName) {
        BigInteger numStyleId = getNumStyleId(numStyleName);
        BigInteger numId = numbering.addNum(numStyleId);
        return new DocNum(numId);
    }

}
