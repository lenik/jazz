package net.bodz.bios.units.text;

import static net.bodz.bas.types.util.ArrayOps.Chars;

import java.io.IOException;
import java.nio.CharBuffer;

public class BreakOrCutLinesUnit extends TextProcessUnit {

    private char       eolChar;
    private boolean    chop;

    private CharBuffer lineBuf;

    public BreakOrCutLinesUnit(int maxLineLength) {
        this(maxLineLength, '\n', true);
    }

    public BreakOrCutLinesUnit(int maxLineLength, char eolChar, boolean chop) {
        this.lineBuf = CharBuffer.allocate(maxLineLength);
        this.eolChar = eolChar;
        this.chop = chop;
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
        lineBuf.reset();
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
                recvl(chars, start, end - start);
                break;
            }
            int next = eol + 1;
            if (chop) {
                if (eol > start && chars[eol - 1] == '\r')
                    eol--;
            } else
                eol = next;
            recvl(chars, start, eol - start);
            // if buf is N*maxLen, then it may be empty now.
            if (lineBuf.length() != 0) {
                String line = lineBuf.toString();
                lineBuf.clear();
                send(line);
            }
            start = next;
        } while (true);
    }

    void recvl(char[] chars, int offset, int length) throws IOException {
        int capacity = lineBuf.capacity();
        int rest = capacity - lineBuf.limit();
        while (rest < length) {
            String cut;
            if (rest == capacity)
                cut = new String(chars, offset, capacity);
            else {
                lineBuf.put(chars, offset, rest);
                cut = lineBuf.toString();
                lineBuf.clear();
            }
            send(cut);
            offset += rest;
            length -= rest;
            rest = capacity;
        }
        lineBuf.put(chars, offset, length);
    }

}
