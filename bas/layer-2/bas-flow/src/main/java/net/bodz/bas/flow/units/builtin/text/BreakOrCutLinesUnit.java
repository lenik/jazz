package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;
import java.nio.CharBuffer;

import org.apache.commons.lang.ArrayUtils;

public class BreakOrCutLinesUnit
        extends TextProcessUnit {

    private char eolChar;
    private boolean chop;

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
        lineBuf.clear();
    }

    @Override
    public void flush()
            throws IOException {
        if (lineBuf.position() != 0) {
            String line = lineBuf.flip().toString();
            lineBuf.clear();
            send(line);
        }
    }

    @Override
    public void recv(char[] chars, int start, int end)
            throws IOException {
        do {
            int newLinePos = ArrayUtils.indexOf(chars, '\n', start);
            if (newLinePos == -1 || newLinePos >= end) {
                recvl(chars, start, end - start);
                break;
            }
            int next = newLinePos + 1;
            if (chop) {
                if (newLinePos > start && chars[newLinePos - 1] == '\r')
                    newLinePos--;
            } else
                newLinePos = next;
            recvl(chars, start, newLinePos - start);
            // if buf is N*maxLen, then it may be empty now.
            flush();
            start = next;
        } while (true);
    }

    void recvl(char[] chars, int offset, int length)
            throws IOException {
        int capacity = lineBuf.capacity();
        assert capacity == lineBuf.limit();
        int free = lineBuf.remaining(); // capacity - lineBuf.limit();
        while (length >= free) {
            String cut;
            if (free == capacity) // buffer empty
                cut = new String(chars, offset, free);
            else {
                cut = lineBuf.put(chars, offset, free).flip().toString();
                // lineBuf.clear();
            }
            send(cut);
            offset += free;
            length -= free;
            free = capacity;
        }
        lineBuf.put(chars, offset, length);
    }

}
