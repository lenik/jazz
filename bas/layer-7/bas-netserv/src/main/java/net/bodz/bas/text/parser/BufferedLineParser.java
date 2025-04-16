package net.bodz.bas.text.parser;

import java.nio.charset.StandardCharsets;

import net.bodz.bas.err.IErrorRecoverer;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.ILookAhead;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public abstract class BufferedLineParser
        implements IBufferedParser,
                   ILineParser {

    static final Logger logger = LoggerFactory.getLogger(BufferedLineParser.class);

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
    public void parse(@NotNull ILookAhead la)
            throws ParseException {
        final byte[] buf = lineBuffer.getBackedArray();
        final int off = lineBuffer.getBackedArrayOffset();
        final int len = lineBuffer.length();
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
            if (stop != off) {
                lineBuffer.delete(0, stop - off);
            }
        }
    }

    @Override
    public void parseLine(@NotNull byte[] line, int off, int len)
            throws ParseException {
        String s = new String(line, off, len, StandardCharsets.UTF_8);
        parseLine(s);
    }

}
