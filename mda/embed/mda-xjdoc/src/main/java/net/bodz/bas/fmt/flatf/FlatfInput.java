package net.bodz.bas.fmt.flatf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class FlatfInput
        implements IFlatfInput {

    BufferedReader reader;

    String piText;
    String sectionName;
    String attributeName;
    StringBuilder attributeText = new StringBuilder();

    public FlatfInput(Reader reader) {
        if (reader == null)
            throw new NullPointerException("reader");
        if (reader instanceof BufferedReader)
            this.reader = (BufferedReader) reader;
        else
            this.reader = new BufferedReader(reader);
    }

    @Override
    public void close()
            throws IOException {
        reader.close();
    }

    @Override
    public int next()
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

            if (line.startsWith("%")) {
                piText = line.substring(1).trim();
                return PI;
            }

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

    @Override
    public String getPiText() {
        return piText;
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
