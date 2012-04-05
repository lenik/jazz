package net.bodz.bas.text.flatf;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

public class FlatfInput
        implements IFlatfInput {

    static final int SECTION_BEGIN = 1;
    static final int ATTRIBUTE = 2;
    static final int EOF = -1;

    BufferedReader reader;

    String sectionName;
    String attributeName;
    StringBuilder attributeText = new StringBuilder();

    @Override
    public boolean nextSection()
            throws ParseException, IOException {
        return next(SECTION_BEGIN);
    }

    @Override
    public boolean nextAttribute()
            throws ParseException, IOException {
        return next(ATTRIBUTE);
    }

    @Override
    public String getSectionName() {
        return sectionName;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String getAttributeText() {
        return attributeText.toString();
    }

    boolean next(int token)
            throws IOException {
        int tok;
        while ((tok = next()) != EOF) {
            if (tok == token)
                return true;
        }
        return false;
    }

    int next()
            throws IOException {
        String line;
        int eq;
        boolean cont = false;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (cont) {
                cont = isCont(line);
                if (cont)
                    line = line.substring(0, line.length() - 1);
                attributeText.append(line);
                if (cont)
                    // attributeText.append('\n'); // OPT: Keep orig format.
                    continue;
                else
                    return ATTRIBUTE;
            }
            if (line.isEmpty())
                continue;
            if (line.startsWith("#"))
                continue;
            if (line.startsWith("[") && line.endsWith("]")) {
                sectionName = line.substring(1, line.length() - 1);
                return SECTION_BEGIN;
            }
            eq = line.indexOf('=');
            if (eq != -1) {
                attributeName = line.substring(0, eq).trim();
                line = line.substring(eq + 1).trim();
                cont = isCont(line);
                if (cont)
                    line = line.substring(0, line.length() - 1);
                attributeText.setLength(0);
                attributeText.append(line);
                if (cont)
                    continue;
                else
                    return ATTRIBUTE;
            }
        }
        return EOF;
    }

    @SuppressWarnings("unused")
    private static final String trimLeft(String s) {
        int len = s.length();
        int begin = s.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isSpaceChar(s.charAt(i))) {
                begin = i;
                break;
            }
        }
        return s.substring(begin);
    }

    /**
     * @return <code>true</code> if <code>str</code> is terminated by '\' but not "\\".
     */
    private static final boolean isCont(String str) {
        int repeat = 0;
        for (int index = str.length() - 1; index >= 0; index--) {
            if (str.charAt(index) == '\\')
                repeat++;
            else
                break;
        }
        return (repeat % 2) == 1;
    }

}
