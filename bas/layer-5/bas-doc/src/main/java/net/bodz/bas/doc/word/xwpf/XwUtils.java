package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.doc.property.HorizAlignment;

public class XwUtils {

    public static void addPlainText(XWPFParagraph par, String s) {
        List<XWPFRun> runs = par.getRuns();
        XWPFRun run;
        if (runs.isEmpty())
            run = par.createRun();
        else
            run = runs.get(runs.size() - 1);
        addPlainText(run, s);
    }

    public static void addPlainText(XWPFRun run, String s) {
        if ("\n".equals(s)) {
            run.addBreak();
            return;
        }

        int start = 0;
        int len = s.length();
        int n = 0;
        while (start < len) {
            int eol = s.indexOf('\n', start);
            if (eol == -1)
                eol = len;
            String line = s.substring(start, eol);
            start = eol + 1;
            if (n++ != 0)
                run.addBreak();
            run.setText(line);
        }
    }

    public static ParagraphAlignment convert(HorizAlignment a) {
        if (a == null)
            return null;
        switch (a) {
        default:
        case LEFT:
            return ParagraphAlignment.LEFT;
        case RIGHT:
            return ParagraphAlignment.RIGHT;
        case CENTER:
            return ParagraphAlignment.CENTER;
        case FILL:
            return ParagraphAlignment.DISTRIBUTE;
        }
    }

    public static HorizAlignment convert(ParagraphAlignment a) {
        switch (a) {
        default:
        case LEFT:
            return HorizAlignment.LEFT;
        case RIGHT:
            return HorizAlignment.RIGHT;
        case CENTER:
            return HorizAlignment.CENTER;
        case DISTRIBUTE:
            return HorizAlignment.FILL;
        }
    }

}
