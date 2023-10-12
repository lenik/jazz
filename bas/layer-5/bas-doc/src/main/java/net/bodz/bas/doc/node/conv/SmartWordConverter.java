package net.bodz.bas.doc.node.conv;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.doc.word.xwpf.XwPredicates;
import net.bodz.bas.doc.word.xwpf.XwRun;

public class SmartWordConverter
        extends WordConverter {

    public SmartWordConverter(XWPFDocument _document) {
        super(_document);
    }

    @Override
    public void chars(String s) {
        XwRun run = x_ptr.closest(XwPredicates.RUN);
        XWPFRun _run = run.getElement();
        _run.setText(s);
    }

}
