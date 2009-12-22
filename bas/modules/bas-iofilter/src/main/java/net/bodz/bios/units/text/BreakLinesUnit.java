package net.bodz.bios.units.text;

import static net.bodz.bas.commons.collection.util.ArrayOps.Chars;

import java.io.IOException;

public class BreakLinesUnit extends TextProcessUnit {

    private char eolChar;
    private boolean chop;

    private StringBuffer lineBuf;

    public BreakLinesUnit() {
        this(16);
    }

    public BreakLinesUnit(int initCapacity) {
        this('\n', true, initCapacity);
    }

    public BreakLinesUnit(char eolChar, boolean chop, int initCapacity) {
        this.eolChar = eolChar;
        this.chop = chop;
        this.lineBuf = new StringBuffer(initCapacity);
    }

    public char getEOLChar() {
        return eolChar;
    }

    public void setEOLChar(char eolChar) {
        this.eolChar = eolChar;
    }

    public boolean isChop() {
        return chop;
    }

    public void setChop(boolean chop) {
        this.chop = chop;
    }

    @Override
    public void reset() {
        lineBuf.setLength(0);
    }

    @Override
    public void flush() throws IOException {
        String s = lineBuf.toString();
        if (!s.isEmpty())
            send(s);
        reset();
    }

    @Override
    public void recv(char[] chars, int start, int end) throws IOException {
        do {
            int eol = Chars.indexOf(chars, start, end, '\n', start);
            if (eol == -1) {
                lineBuf.append(chars, start, end - start);
                break;
            }
            int next = eol + 1;
            if (chop) {
                if (eol > start && chars[eol - 1] == '\r')
                    eol--;
            } else
                eol = next;
            String line;
            if (lineBuf.length() == 0) {
                line = new String(chars, start, eol - start);
            } else {
                lineBuf.append(chars, start, eol - start);
                line = lineBuf.toString();
                lineBuf.setLength(0);
            }
            send(line);
            start = next;
        } while (true);
    }

}
