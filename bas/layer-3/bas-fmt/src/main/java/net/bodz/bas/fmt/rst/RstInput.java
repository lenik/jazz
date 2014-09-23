package net.bodz.bas.fmt.rst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.c.java.util.ArrayAndScalar;
import net.bodz.bas.c.java.util.Arrays;

public class RstInput
        implements IRstInput {

    BufferedReader reader;

    String elementName;
    String elementArguments[];
    String attributeName;
    StringBuilder attributeData = new StringBuilder();

    public RstInput(Reader reader) {
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
        int colon;
        boolean cont = false;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (cont) {
                cont = isCont(line);
                if (cont)
                    line = line.substring(0, line.length() - 1);
                attributeData.append(line);
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

            if (line.endsWith("{")) {
                line = line.substring(0, line.length() - 1);
                ArrayAndScalar<String[], String> pair = Arrays.shift(line.split("\\s+"));
                elementName = pair.scalar;
                elementArguments = pair.array;
                return ELEMENT_BEGIN;
            }

            if (line.equals("}"))
                return ELEMENT_END;

            // otherwise:
            colon = line.indexOf(':');
            if (colon != -1) {
                attributeName = line.substring(0, colon).trim();
                line = line.substring(colon + 1).trim();
                cont = isCont(line);
                if (cont)
                    line = line.substring(0, line.length() - 1);
                attributeData.setLength(0);
                attributeData.append(line);
                if (cont)
                    continue;
                else
                    return ATTRIBUTE;
            }
        }
        return EOF;
    }

    @Override
    public String getElementName() {
        return elementName;
    }

    public String[] getElementArguments() {
        return elementArguments;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String getAttributeData() {
        return attributeData.toString();
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
