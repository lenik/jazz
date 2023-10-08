package net.bodz.bas.doc.node.conv;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.doc.word.xwpf.IXwpfNode;
import net.bodz.bas.doc.word.xwpf.XwpfPredicates;
import net.bodz.bas.doc.word.xwpf.XwpfRunNode;

public class SmartWordConverter
        extends StrictWordConverter {

    public SmartWordConverter(XWPFDocument _document) {
        super(_document);
    }

    @Override
    public void chars(String s) {
        IXwpfNode run = n_ptr.closest(XwpfPredicates.IS_RUN);
        switch (run.getType()) {
        case RUN:
            XwpfRunNode r = (XwpfRunNode) run;
            XWPFRun _run = r.getElement();
            _run.setText(s);
            break;
        default:
        }
    }

}
