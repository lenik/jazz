package net.bodz.bas.fmt.records;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitorx;

/**
 * Preferred extension: txts
 */
public class TextRecords
        extends AbstractSeqRecords<String> {

    private char leftAngleChar = '<';
    private char rightAngleChar = '>';
    private char dashChar = '-';

    private String defaultPartName = "0";
    private String nextPartName;
    private String lastPartName;

    public TextRecords(IStreamInputSource source) {
        super(source);
    }

    public TextRecords(File file, String encoding) {
        this(new FileResource(file, encoding));
    }

    class Iter
            extends AbstractMitorx<String, IOException> {

        private ICharIn in;

        static final int S_TEXT = 0;
        static final int S_LT = S_TEXT + 1;
        static final int S_LTpre = S_LT + 1;
        static final int S_LT1 = S_LTpre + 1;
        static final int S_LT2 = S_LT1 + 1;
        static final int S_X = S_LT2 + 1;
        static final int S_GT1 = S_X + 1;
        static final int S_GT2 = S_GT1 + 1;
        static final int S_GTpost = S_GT2 + 1;

        @Override
        public String _next()
                throws IOException {
            if (in == null)
                in = source.newCharIn();

            StringBuilder sb = new StringBuilder();
            int ltStart = -1;
            int xStart = -1, xEnd = -1;

            int c = -1;
            int bsBeforeLt = 0;
            int state = S_TEXT;
            int pos = -1;
            while (true) {
                pos++;

                c = in.read();
                if (c == -1)
                    break;
                sb.append((char) c);

                switch (state) {
                case S_TEXT:
                    if (c == '\\')
                        bsBeforeLt++;
                    else if (c == leftAngleChar) {
                        state = S_LT;
                        ltStart = pos;
                    } else {
                        bsBeforeLt = 0;
                        continue;
                    }
                    break;

                case S_LT:
                    // if (ch == '!') state = S_LT0; else state = S_TEXT;
                    // break;
                    // case S_LT0:
                    if (c == dashChar)
                        state = S_LT1;
                    else
                        state = S_TEXT;
                    break;

                case S_LT1:
                    if (c == dashChar)
                        state = S_LT2;
                    else
                        state = S_TEXT;
                    break;

                case S_LT2:
                    if (c == dashChar) // <--, -*
                        continue;
                    state = S_X;
                    xStart = pos;
                    // and more...

                case S_X:
                    if (c == dashChar)
                        state = S_GT1;
                    else if (c == '\n' || c == '\r')
                        state = S_TEXT;
                    break;

                case S_GT1:
                    if (c == dashChar)
                        state = S_GT2;
                    else
                        state = S_X;
                    break;

                case S_GT2:
                    if (c == dashChar)
                        state = S_GT2;
                    else if (c == rightAngleChar) {
                        state = S_TEXT;
                        if (bsBeforeLt % 2 == 0) {
                            String x2 = sb.substring(xStart, xEnd);
                            lastPartName = nextPartName;
                            nextPartName = x2.trim();
                            sb.setLength(ltStart - bsBeforeLt / 2);
                            return sb.toString();
                        } else {
                            sb.delete(ltStart - bsBeforeLt / 2 - 1, ltStart);
                        }
                    } else
                        state = S_X;
                    break;
                }

                if (state == S_X)
                    xEnd = pos + 1;
            } // for in.read

            lastPartName = nextPartName;
            nextPartName = null;

            if (sb.length() == 0)
                return end();
            else
                return sb.toString();
        }
    }

    @Override
    public Mitorx<String, IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

    /**
     */
    public String getFormatChars() {
        StringBuilder sb = new StringBuilder();
        sb.append(leftAngleChar);
        sb.append(dashChar);
        sb.append(rightAngleChar);
        return sb.toString();
    }

    /**
     * Set the format characters.
     * 
     * @param l_r
     *            You can set the format to <code>(==foo==)</code> or <code>{~~bar~~}</code> with
     *            "(=)" or "{~}". By default, it's <code>&lt;-&gt;</code>.
     */
    public void setFormatChars(String l_r) {
        if (l_r.length() != 3)
            throw new IllegalArgumentException("Bad format chars: " + l_r);
        leftAngleChar = l_r.charAt(0);
        dashChar = l_r.charAt(1);
        rightAngleChar = l_r.charAt(2);
    }

    public char getLeftAngleChar() {
        return leftAngleChar;
    }

    public void setLeftAngleChar(char leftAngleChar) {
        this.leftAngleChar = leftAngleChar;
    }

    public char getDashChar() {
        return dashChar;
    }

    public void setDashChar(char dashChar) {
        this.dashChar = dashChar;
    }

    public void setLastPartName(String lastPartName) {
        this.lastPartName = lastPartName;
    }

    public String getDefaultPartName() {
        return defaultPartName;
    }

    public void setDefaultPartName(String defaultPartName) {
        this.defaultPartName = defaultPartName;
    }

    public String getLastPartName() {
        return lastPartName == null ? defaultPartName : lastPartName;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String part : this) {
            map.put(getLastPartName(), part);
        }
        return map;
    }

    public static Map<String, String> toMap(IStreamInputSource source, String formatChars) {
        TextRecords records = new TextRecords(source);
        records.setFormatChars(formatChars);
        return records.toMap();
    }

}
