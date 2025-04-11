package net.bodz.bas.parser;

import java.nio.charset.StandardCharsets;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.ILookAhead;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public abstract class BufferedLineParser
        implements IBufferedParser,
                   ILineParser {

    @NotNull
    ByteArrayBuffer lineBuffer = new ByteArrayBuffer(4096);

    IErrorRecoverer errorRecoverer;

    public IErrorRecoverer getErrorRecoverer() {
        return errorRecoverer;
    }

    public void setErrorRecoverer(IErrorRecoverer errorRecoverer) {
        this.errorRecoverer = errorRecoverer;
    }

    @Override
    public void putOctet(byte octet) {
        lineBuffer.append(octet);
    }

    @Override
    public void putOctet(int octet) {
        lineBuffer.append(octet);
    }

    @Override
    public int getBufferLength() {
        return lineBuffer.length();
    }

    @Override
    public void resetBuffer() {
        lineBuffer.clear();
    }

    @Override
    public byte[] bufferCopy() {
        return lineBuffer.toByteArray();
    }

    @Override
    public void parse(ILookAhead la)
            throws ParseException {
        byte[] buf = lineBuffer.getBackedArray();
        int off = lineBuffer.getBackedArrayOffset();
        int len = lineBuffer.length();
        int look = off;
        int pos = off;
        int stop = off;

        try {
            for (int n = 0; n < len; n++) {
                byte octet = buf[look++];
                switch (octet) {
                    case '\r':
                        continue;
                    case '\n':
                        try {
                            parseLine(buf, off, pos - off);
                        } catch (ParseException e) {
                            if (errorRecoverer == null || !errorRecoverer.recoverError(e))
                                throw e;
                        }
                        stop = look;
                        break;
                }
                pos = look;
            }
        } finally {
            lineBuffer.delete(0, stop - off);
        }
    }

    @Override
    public void parseLine(byte[] line, int off, int len)
            throws ParseException {
        String s = new String(line, StandardCharsets.UTF_8);
        parseLine(s);
    }

}
